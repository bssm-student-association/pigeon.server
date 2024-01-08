package com.project.insert.domain.post.service;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import com.project.insert.domain.post.domain.repository.PostRepository;
import com.project.insert.domain.post.presentation.dto.PostRequestDto;
import com.project.insert.domain.post.presentation.dto.PostResponseDto;
import com.project.insert.domain.post.presentation.dto.PostSummaryDto;
import com.project.insert.domain.user.UserFacade;
import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.domain.authority.Authority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserFacade userFacade;


    @Transactional
    public void createPost(PostRequestDto dto) {
        User author = userFacade.getCurrentUser();

        postRepository.save(new Post(
                dto.getTitle(),
                dto.getContent(),
                dto.getPostType(),
                author));
    }

    public Post readOne(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public PostSummaryDto readSummary() {
        List<Post> suggestion = postRepository.findByPostTypeOrderByCreatedAtDesc(PostType.SUGGESTION, PageRequest.of(0, 4));
        List<Post> project = postRepository.findByPostTypeOrderByCreatedAtDesc(PostType.PROJECT, PageRequest.of(0, 4));
        List<Post> mentoring = postRepository.findByPostTypeOrderByCreatedAtDesc(PostType.MENTORING, PageRequest.of(0, 4));

        List<PostResponseDto> suggestionDto = new ArrayList<>();
        List<PostResponseDto> projectDto = new ArrayList<>();
        List<PostResponseDto> mentoringDto = new ArrayList<>();

        for(Post item : suggestion) { suggestionDto.add(new PostResponseDto(item)); }
        for(Post item : project) { projectDto.add(new PostResponseDto(item)); }
        for(Post item : mentoring) { mentoringDto.add(new PostResponseDto(item)); }

        return new PostSummaryDto(suggestionDto, projectDto, mentoringDto);
    }


    public List<PostResponseDto> readByPostType(PostType postType){
        List<PostResponseDto> postDto = new ArrayList<>();
        List<Post> posts = postRepository.findByPostType(postType);
        for(Post post : posts) { postDto.add(new PostResponseDto(post)); }
        return postDto;
    }

    @Transactional
    public void deletePost(Long id){
        User author = userFacade.getCurrentUser();
        if(author.getAuthority().equals(Authority.ADMIN)) {
            postRepository.deleteById(id);
        }
    }
}

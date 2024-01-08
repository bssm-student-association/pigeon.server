package com.project.insert.domain.post.presentation;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import com.project.insert.domain.post.presentation.dto.PostResponseDto;
import com.project.insert.domain.post.presentation.dto.PostSummaryDto;
import com.project.insert.domain.post.service.PostService;
import com.project.insert.domain.post.presentation.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/apply")
    public void createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
    }

    @GetMapping("/summary")
    public PostSummaryDto readSummary() {
        return postService.readSummary();
    }

    @GetMapping("/{id}")
    public Post readOne(@PathVariable Long id) {
        return postService.readOne(id);
    }

    @GetMapping("/detail/{postType}")
    public List<PostResponseDto> readByPostType(@PathVariable PostType postType) {
        return postService.readByPostType(postType);
    }

    @DeleteMapping("/remove/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
}

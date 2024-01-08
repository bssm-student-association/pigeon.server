package com.project.insert.domain.post.presentation.dto;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import com.project.insert.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private PostType postType;

    private LocalDate createdAt;

    private User author;
    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
        this.postType = post.getPostType();
    }
}

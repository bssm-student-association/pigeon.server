package com.project.insert.domain.comment.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;

    private Long postId;

    public CommentRequestDto(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}


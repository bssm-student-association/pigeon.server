package com.project.insert.domain.comment.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private String content;

    public CommentUpdateRequestDto(String content) {
        this.content = content;
    }
}

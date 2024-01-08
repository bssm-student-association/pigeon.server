package com.project.insert.domain.answer.presentation.dto;

import com.project.insert.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequestDto {
    private String content;

    private Long postId;

    public AnswerRequestDto(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}

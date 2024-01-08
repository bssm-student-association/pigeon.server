package com.project.insert.domain.answer.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerUpdateRequestDto {
    private String content;

    public AnswerUpdateRequestDto(String content) {
        this.content = content;
    }
}

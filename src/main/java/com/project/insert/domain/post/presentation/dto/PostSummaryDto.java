package com.project.insert.domain.post.presentation.dto;

import com.project.insert.domain.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostSummaryDto {
    private List<PostResponseDto> suggestion;

    private List<PostResponseDto> project;

    private List<PostResponseDto> mentoring;

    public PostSummaryDto(List<PostResponseDto> suggestion, List<PostResponseDto> project, List<PostResponseDto> mentoring) {
        this.suggestion = suggestion;
        this.project = project;
        this.mentoring = mentoring;
    }
}

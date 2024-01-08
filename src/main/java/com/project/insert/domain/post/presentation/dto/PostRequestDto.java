package com.project.insert.domain.post.presentation.dto;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    private String content;
    private PostType postType;
}

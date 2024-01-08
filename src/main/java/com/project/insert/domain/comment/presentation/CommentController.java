package com.project.insert.domain.comment.presentation;

import com.project.insert.domain.comment.presentation.dto.CommentRequestDto;
import com.project.insert.domain.comment.presentation.dto.CommentUpdateRequestDto;
import com.project.insert.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public void createComment(@RequestBody CommentRequestDto dto) {
        commentService.createComment(dto);
    }

    @PutMapping("/edit/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto dto) {
        commentService.updateComment(id, dto);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

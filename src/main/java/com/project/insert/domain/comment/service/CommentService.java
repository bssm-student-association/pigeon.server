package com.project.insert.domain.comment.service;

import com.project.insert.domain.comment.domain.Comment;
import com.project.insert.domain.comment.domain.repository.CommentRepository;
import com.project.insert.domain.comment.presentation.dto.CommentRequestDto;
import com.project.insert.domain.comment.presentation.dto.CommentUpdateRequestDto;
import com.project.insert.domain.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;

    public void createComment(CommentRequestDto dto) {
        commentRepository.save(new Comment(
                dto.getContent(),
                dto.getPostId(),
                userFacade.getCurrentUser()
        ));
    }

    public void updateComment(Long id, CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.update(dto.getContent());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

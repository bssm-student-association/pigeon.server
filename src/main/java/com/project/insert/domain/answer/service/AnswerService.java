package com.project.insert.domain.answer.service;

import com.project.insert.domain.answer.domain.Answer;
import com.project.insert.domain.answer.domain.repository.AnswerRepository;
import com.project.insert.domain.answer.presentation.dto.AnswerRequestDto;
import com.project.insert.domain.answer.presentation.dto.AnswerUpdateRequestDto;
import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import com.project.insert.domain.post.domain.repository.PostRepository;
import com.project.insert.domain.user.UserFacade;
import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.domain.authority.Authority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AnswerService {
    private final PostRepository postRepository;
    private final AnswerRepository answerRepository;
    private final UserFacade userFacade;

    @Transactional
    public void createAnswer(AnswerRequestDto dto) {
        Post post = postRepository.findById(dto.getPostId()).orElseThrow();
        User author = userFacade.getCurrentUser();

        if(post.getPostType().equals(PostType.SUGGESTION)) {
            answerRepository.save(new Answer(dto.getContent(), dto.getPostId(), author));
        }
    }

    @Transactional
    public void updateAnswer(Long id, AnswerUpdateRequestDto dto) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        answer.update(dto.getContent());
    }

    @Transactional
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}

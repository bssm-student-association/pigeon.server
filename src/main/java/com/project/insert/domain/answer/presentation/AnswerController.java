package com.project.insert.domain.answer.presentation;

import com.project.insert.domain.answer.presentation.dto.AnswerRequestDto;
import com.project.insert.domain.answer.presentation.dto.AnswerUpdateRequestDto;
import com.project.insert.domain.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/apply")
    public void createAnswer(@RequestBody AnswerRequestDto dto) {
        answerService.createAnswer(dto);
    }

    @PutMapping("/edit/{id}")
    public void updateAnswer(@PathVariable Long id, @RequestBody AnswerUpdateRequestDto dto) {
        answerService.updateAnswer(id, dto);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
}

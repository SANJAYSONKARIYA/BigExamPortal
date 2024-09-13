package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Answer;
import com.example.demo.Services.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Answer>> getUserAnswers(@PathVariable String userId) {
        List<Answer> answers = answerService.getUserAnswers(userId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
        if (answer == null || answer.getQuestionId() == null || answer.getSelectedOption() == null || answer.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Answer savedAnswer = answerService.saveAnswer(answer);
        return ResponseEntity.ok(savedAnswer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer updatedAnswer) {
        Answer existingAnswer = answerService.updateAnswer(id, updatedAnswer);
        if (existingAnswer != null) {
            return ResponseEntity.ok(existingAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        if (answerService.deleteAnswer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

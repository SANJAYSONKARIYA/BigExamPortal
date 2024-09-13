package com.example.demo.Controller;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Answer;
import com.example.demo.repositories.AnswerRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private AnswerRepository answerRepository;

    // Endpoint to handle the submitted answers
    @PostMapping("/submitAnswers")
    public String submitAnswers(@RequestBody List<Answer> answers) {
        answerRepository.saveAll(answers);
        return "Answers submitted successfully!";
    }
    
   
}


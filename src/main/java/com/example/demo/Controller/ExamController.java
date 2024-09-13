package com.example.demo.Controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Answer;
import com.example.demo.Services.AnswerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExamController {

    @Autowired
    private AnswerService answerService;

    // Endpoint to submit the answers
    @PostMapping("/submit-answers")
    public String submitAnswers(@RequestBody Map<Long, String> userAnswers, @RequestParam String userId) {
        // userAnswers contains the question ID as the key and the selected option as the value
        List<Answer> answers = new ArrayList<>();

        for (Map.Entry<Long, String> entry : userAnswers.entrySet()) {
            Long questionId = entry.getKey();
            String selectedOption = entry.getValue();
            
            // Create a new Answer object and add it to the list
            Answer answer = new Answer(questionId, selectedOption, userId);
            answers.add(answer);
        }

        // Save all the answers to the database
        answerService.saveAllAnswers(answers);

        return "Answers submitted successfully!";
    }
}



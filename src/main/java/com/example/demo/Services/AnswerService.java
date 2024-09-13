package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Answer;
import com.example.demo.repositories.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getUserAnswers(String userId) {
        return answerRepository.findByUserId(userId);
    }

    public Answer saveAnswer(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Long id, Answer updatedAnswer) {
        Optional<Answer> existingAnswerOpt = answerRepository.findById(id);
        if (existingAnswerOpt.isPresent()) {
            Answer existingAnswer = existingAnswerOpt.get();
            existingAnswer.setQuestionId(updatedAnswer.getQuestionId());
            existingAnswer.setSelectedOption(updatedAnswer.getSelectedOption());
            existingAnswer.setUserId(updatedAnswer.getUserId());
            return answerRepository.save(existingAnswer);
        } else {
            return null;
        }
    }

    public boolean deleteAnswer(Long id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

	public List<Answer> saveAllAnswers(List<Answer> answers) {
		// TODO Auto-generated method stub
		
		return answerRepository.saveAll(answers);
		
	}
}

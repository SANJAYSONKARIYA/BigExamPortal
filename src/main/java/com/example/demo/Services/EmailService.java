package com.example.demo.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FeedbackForm;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendFeedbackEmail(FeedbackForm feedbackForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("sanjaysonkariya8561@gmail.com");
        message.setSubject(feedbackForm.getSubject());
        message.setText("Message from: " + feedbackForm.getEmail() + "\n\n" + "Customer Name: " + feedbackForm.getName() + "\n\n"  + "Subject: " + feedbackForm.getSubject() + "\n\n"  + "Message: " + feedbackForm.getMessage());
        mailSender.send(message);
    }
}



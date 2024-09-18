package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Services.EmailService;
import com.example.demo.dto.FeedbackForm;

@Controller
@RequestMapping("/contact")
public class FeedbackController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String showFeedbackForm(Model model) {
        return "contact"; // Ensure this matches the HTML file name
    }

    @PostMapping("/feedback")
    public String sendFeedback(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {

        FeedbackForm feedbackForm = new FeedbackForm();
        feedbackForm.setName(name);
        feedbackForm.setEmail(email);
        feedbackForm.setSubject(subject);
        feedbackForm.setMessage(message);

        try {
            emailService.sendFeedbackEmail(feedbackForm);
            redirectAttributes.addFlashAttribute("message", "Your message has been sent. Thank you!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to send message. Please try again later.");
            e.printStackTrace();
        }

        return "redirect:/contact";
    }

}



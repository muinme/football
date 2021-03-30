package com.example.football.api.controllers;

import com.example.football.infrastructure.mail.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @GetMapping("/email/send")
    public String sendSimpleEmail() {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("Test Email");
        message.setText("Pham Anh Tu");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }

}

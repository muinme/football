package com.example.football.services.Impl;

import com.example.football.models.PostMatchTeam;
import com.example.football.models.RequestPitch;
import com.example.football.models.User;
import com.example.football.repositories.UserRepository;
import com.example.football.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public String sendEmail(String username, PostMatchTeam postMatchTeam) {
        return null;
    }

    @Override
    public String sendEmailBatDoi(String username, Integer wait_match_team_id) {
        User user = userRepository.findByUsername(username);
        User userPost = userRepository.findByPostId(wait_match_team_id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userPost.getEmail());
        message.setSubject("<FOOTBALL>You receive a soccer friendly request");
        message.setText("Dear "+ userPost.getFullname() + "," + "\n" +
                "\n" +
                "People: "+ user.getFullname() + " want match your post" + "\n" +
                "Please visit the website http://traibonglan.com/login confirm! Thank you \n" +
                "\n" +
                "System sincerely,\n" +
                "Admin");
        // Send Message!
        this.emailSender.send(message);


        return "Email sent";
    }

    @Override
    public String sendEmailDatSan(String username, RequestPitch requestPitch) {
        return null;
    }
}
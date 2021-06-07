package com.example.football.services.Impl;

import com.example.football.models.PostMatchTeam;
import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;
import com.example.football.models.User;
import com.example.football.repositories.PostMatchTeamRepository;
import com.example.football.repositories.RequestMatchRepository;
import com.example.football.repositories.RequestPitchRepository;
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
    private RequestPitchRepository requestPitchRepository;

    @Autowired
    private RequestMatchRepository requestMatchRepository;

    @Autowired
    private PostMatchTeamRepository postMatchTeamRepository;

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
    public String sendEmailDatSan(String username, Integer pitch_id) {
        String s = String.valueOf(pitch_id);
        User user = userRepository.findByUsername(username);
        User userPitch = userRepository.findByPitchId(s);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userPitch.getEmail());
        message.setSubject("<FOOTBALL>You received a request to book a football field");
        message.setText("Dear "+ userPitch.getFullname() + "," + "\n" +
                "\n" +
                "People: "+ user.getFullname() + " want retal your pitch" + "\n" +
                "Please visit the website http://traibonglan.com confirm! Thank you \n" +
                "\n" +
                "System sincerely,\n" +
                "Admin");
        // Send Message!
        this.emailSender.send(message);


        return "Email sent";
    }

    @Override
    public String sendEmailDatSanThanhCong(Integer request_pitch_id) {
        RequestPitch requestPitch = requestPitchRepository.findById(request_pitch_id).get();
        String s = String.valueOf(requestPitch.getPitch_id());
        User user = userRepository.findById(requestPitch.getUser_id()).get();
        User userPitch = userRepository.findByPitchId(s);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("<FOOTBALL>Your request to rent a football field has been successful");
        message.setText("Dear "+ user.getFullname() + "," + "\n" +
                "\n" +
                "Owner: "+ userPitch.getFullname() + "agree to the request to rent the yard" + "\n" +
                "Please contact the yard owner with the information on the website http://traibonglan.com ! Thank you \n" +
                "\n" +
                "System sincerely,\n" +
                "Admin");
        // Send Message!
        this.emailSender.send(message);
        return "Email sent";
    }

    @Override
    public String sendEmailBatDoiThanhCong( Integer request_match_id) {
        RequestMatch requestMatch = requestMatchRepository.findById(request_match_id).get();
        User user = userRepository.findById(requestMatch.getUser_id()).get();
        PostMatchTeam postMatchTeam = postMatchTeamRepository.findById(requestMatch.getWait_match_team_id()).get();
        String s = String.valueOf(postMatchTeam.getFootball_id());
        User userFootBall = userRepository.findByFootBallId(s);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("<FOOTBALL>Your request to match a team has been successful");
        message.setText("Dear "+ user.getFullname() + "," + "\n" +
                "\n" +
                "Caption: "+ userFootBall.getFullname() + "agree to the request to match" + "\n" +
                "Please contact the caption with the information on the website http://traibonglan.com ! Thank you \n" +
                "\n" +
                "System sincerely,\n" +
                "Admin");
        // Send Message!
        this.emailSender.send(message);
        return "Email sent";

    }
}
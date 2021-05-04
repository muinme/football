package com.example.football.services.Impl;

import com.example.football.models.RequestPitch;
import com.example.football.repositories.RequestPitchRepository;
import com.example.football.repositories.UserRepository;
import com.example.football.services.MailService;
import com.example.football.services.RequestPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestPitchServiceImpl implements RequestPitchService {

    @Autowired
    private RequestPitchRepository requestPitchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Override
    public RequestPitch createRequestPitch(Integer pitch_id, Integer pitch_detail_id, String username) {

        RequestPitch requestPitch = new RequestPitch();
        Integer user_id = userRepository.getIdByUserName(username);
        System.out.println(username);
        requestPitch.setPitch_id(pitch_id);
        requestPitch.setPitch_detail_id(pitch_detail_id);
        requestPitch.setUser_id(user_id);
        requestPitch.setCreated(new Date());
        requestPitch.setStatus("0");
//        mailService.sendEmailBatDoi(username, id);
        return requestPitchRepository.save(requestPitch);
    }
    @Override
    public List<RequestPitch> getRequestPitchByUsername(String username) {
        return requestPitchRepository.findByUsername(username);

    }

    @Override
    public RequestPitch getByIdRequestPitch(Integer id) {
        return requestPitchRepository.findById(id).get();
    }

    @Override
    public void saveRequestPitch(RequestPitch requestPitch) {
        requestPitchRepository.save(requestPitch);
    }

    @Override
    public List<RequestPitch> listAllRequestPitch() {
        return requestPitchRepository.findAll();
    }

}

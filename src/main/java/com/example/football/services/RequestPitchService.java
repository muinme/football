package com.example.football.services;

import com.example.football.models.RequestMatch;
import com.example.football.models.RequestPitch;

import java.util.List;

public interface RequestPitchService {
    RequestPitch createRequestPitch(Integer pitch_id, Integer pitch_detail_id, String username);

    List<RequestPitch> getRequestPitchByUsername(String username);

    RequestPitch getByIdRequestPitch(Integer id);

    void saveRequestPitch(RequestPitch requestPitch);

    List<RequestPitch> listAllRequestPitch();

    Integer getSlWait(Integer user_id);

    Integer getSlWaitPitch(Integer pitch_id);
}

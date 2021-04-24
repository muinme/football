package com.example.football.services;

import com.example.football.models.RequestPitch;

import java.util.List;

public interface RequestPitchService {
    RequestPitch createRequestPitch(RequestPitch requestPitch);

    RequestPitch getByIdRequestPitch(Integer id);

    void saveRequestPitch(RequestPitch requestPitch);

    List<RequestPitch> listAllRequestPitch();
}

package com.example.football.services;

import com.example.football.models.RequestMatch;

import java.util.List;

public interface RequestMatchService {
    RequestMatch createRequestMatch(RequestMatch requestMatch);

    RequestMatch getByIdRequestMatch(Integer id);

    void saveRequestMatch(RequestMatch requestMatch);

    List<RequestMatch> listAllRequestMatch();
}

package com.example.football.services;

import com.example.football.models.RequestMatch;

import java.util.List;

public interface RequestMatchService {
    RequestMatch createRequestMatch(Integer id,String username);

    RequestMatch getByIdRequestMatch(Integer id);

    void saveRequestMatch(RequestMatch requestMatch);
    List<RequestMatch> getRequestMatchByUsername(String username);
    List<RequestMatch> listAllRequestMatch();
}

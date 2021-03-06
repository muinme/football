package com.example.football.services;

import com.example.football.models.DetailPitch;
import com.example.football.models.HistoryMatch;

import java.util.List;

public interface HistoryMatchService {
    HistoryMatch createHistoryMatch(HistoryMatch historyMatch);

    HistoryMatch getByIdHistoryMatch(Integer id);

    void saveHistoryMatch1(HistoryMatch historyMatch);
    void saveHistoryMatch2(HistoryMatch historyMatch);

    List<HistoryMatch> listAllHistoryMatch();
}

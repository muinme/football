package com.example.football.services;

import com.example.football.models.DetailPitch;
import com.example.football.models.HistoryMatch;
import com.example.football.models.PieInfo;

import java.util.List;

public interface HistoryMatchService {
    HistoryMatch createHistoryMatch(HistoryMatch historyMatch);

    HistoryMatch getByIdHistoryMatch(Integer id);

    HistoryMatch saveHistoryMatch1(Integer id);
    HistoryMatch saveHistoryMatch2(Integer id);

    List<HistoryMatch> listAllHistoryMatch();
    List<PieInfo> pieInfoList();
}

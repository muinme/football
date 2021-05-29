package com.example.football.services;

import com.example.football.models.DetailPitch;
import com.example.football.models.HistoryMatch;
import com.example.football.models.PieInfo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface HistoryMatchService {

    HistoryMatch createHistoryMatch(HistoryMatch historyMatch);

    HistoryMatch getByIdHistoryMatch(Integer id);

    HistoryMatch saveHistoryMatch1(Integer id);

    HistoryMatch saveHistoryMatch2(Integer id);

    List<HistoryMatch> listAllHistoryMatch();

    List<PieInfo> pieInfoList();

    Integer findTcMatch(Integer user_id);

    Integer findTbMatch(Integer user_id);

    Integer findTcOrderTeam(Integer football_id);

    Integer findTbOrderTeam(Integer football_id);
}

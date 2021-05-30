package com.example.football.services;

import com.example.football.models.HistoryRental;
import com.example.football.models.PieInfo;

import java.util.List;

public interface HistoryRentalService {
    HistoryRental saveHistoryRental1(Integer id);

    HistoryRental saveHistoryRental2(Integer id);

    List<HistoryRental> listAllHistoryRental();

    List<PieInfo> pieInfoList();

    Integer findTcOrder(Integer user_id);

    Integer findTbOrder(Integer user_id);

    Integer findTcOrderPitch(Integer pitch_id);

    Integer findTbOrderPitch(Integer pitch_id);

    List<HistoryRental> listHistoryRentalByUserName(String username);
}

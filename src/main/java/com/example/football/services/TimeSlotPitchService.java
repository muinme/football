package com.example.football.services;

import com.example.football.models.PieInfo;
import com.example.football.models.Price;
import com.example.football.models.TimeSlotPitch;

import java.util.List;

public interface TimeSlotPitchService {
    TimeSlotPitch getTimeById(Integer id);
    List<PieInfo> pieInfoList();
}

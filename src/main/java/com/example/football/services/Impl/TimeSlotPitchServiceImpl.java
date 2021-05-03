package com.example.football.services.Impl;

import com.example.football.models.TimeSlotPitch;
import com.example.football.repositories.TimeSlotPitchRepository;
import com.example.football.services.TimeSlotPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotPitchServiceImpl implements TimeSlotPitchService {
    @Autowired
    private TimeSlotPitchRepository timeSlotPitchRepository;

    @Override
    public TimeSlotPitch getTimeById(Integer id) {
        return timeSlotPitchRepository.findById(id).get();
    }
}

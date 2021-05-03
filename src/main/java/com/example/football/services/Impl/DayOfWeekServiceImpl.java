package com.example.football.services.Impl;

import com.example.football.models.DayOfWeek;
import com.example.football.repositories.DayOfWeekRepository;
import com.example.football.services.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayOfWeekServiceImpl implements DayOfWeekService {
    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    @Override
    public DayOfWeek getDayById(Integer id) {
        return dayOfWeekRepository.findById(id).get();
    }
}

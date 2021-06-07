package com.example.football.services.Impl;

import com.example.football.models.Revenue;
import com.example.football.repositories.DetailPitchRepository;
import com.example.football.repositories.RevenueRepository;
import com.example.football.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @Override
    public List<Revenue> findRevenue(Integer pitch_id, Integer number_pitch_id) {
        return revenueRepository.findRevenue(pitch_id, number_pitch_id);
    }

    @Override
    public Revenue sumRevenue(Integer pitch_id, Integer number_pitch_id) {
        Integer revenue = detailPitchRepository.sumRevenue(pitch_id, number_pitch_id);
        Revenue newRevenue = new Revenue();
        newRevenue.setMoney_revenue(revenue);
        newRevenue.setNumber_pitch_id(number_pitch_id);
        newRevenue.setPitch_id(pitch_id);
        createRevenue(newRevenue);
        return newRevenue;
    }

    @Override
    public Revenue createRevenue(Revenue revenue) {
        revenue.setCreated(new Date());
        return revenueRepository.save(revenue);
    }

    @Override
    public Integer sumRevenuePitch(Integer pitch_id) {
        Integer revenue = detailPitchRepository.sumRevenuePitch(pitch_id);
        return revenue;
    }

    @Override
    public Integer sumRevenueAll(Integer pitch_id) {
        return revenueRepository.sumRevenueAll(pitch_id);
    }
}

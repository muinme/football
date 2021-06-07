package com.example.football.services;

import com.example.football.models.Revenue;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RevenueService {

    List<Revenue> findRevenue(Integer pitch_id, Integer number_pitch_id);

    Revenue sumRevenue(Integer pitch_id, Integer number_pitch_id);

    Revenue createRevenue(Revenue revenue);

    Integer sumRevenuePitch(Integer pitch_id);

    Integer sumRevenueAll(Integer pitch_id);
}

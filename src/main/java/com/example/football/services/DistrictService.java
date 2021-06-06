package com.example.football.services;

import com.example.football.models.District;
import com.example.football.models.Province;

import java.util.List;

public interface DistrictService {
    List<District> findByProvinceCode(Integer province_code);
}

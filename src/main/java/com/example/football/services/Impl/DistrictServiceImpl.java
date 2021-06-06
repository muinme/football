package com.example.football.services.Impl;

import com.example.football.models.District;
import com.example.football.repositories.DistrictRepository;
import com.example.football.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public List<District> findByProvinceCode(Integer province_code) {
        return districtRepository.findByProvinceCode(province_code);
    }
}

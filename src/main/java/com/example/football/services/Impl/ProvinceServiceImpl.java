package com.example.football.services.Impl;

import com.example.football.models.Province;
import com.example.football.repositories.ProvinceRepository;
import com.example.football.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public List<Province> getAll() {
        return provinceRepository.findAll();
    }
}

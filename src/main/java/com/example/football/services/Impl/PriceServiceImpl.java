package com.example.football.services.Impl;

import com.example.football.models.Pitch;
import com.example.football.models.Price;
import com.example.football.models.User;
import com.example.football.repositories.PitchRepository;
import com.example.football.repositories.PriceRepository;
import com.example.football.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Price getPriceById(Integer id) {
        return priceRepository.findById(id).get();
    }

    @Override
    public Price createPrice(Price price) {
        return priceRepository.save(price);
    }
}

package com.example.football.api.controllers;

import com.example.football.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping("/football")
@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping(value = {"/district/{province_code}"})
    public ResponseEntity<?> getAll(@PathVariable Integer province_code) throws Exception {

        return new ResponseEntity<>(districtService.findByProvinceCode(province_code), HttpStatus.OK);
    }
}

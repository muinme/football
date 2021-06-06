package com.example.football.api.controllers;

import com.example.football.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping("/football")
@RestController
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;

    @GetMapping(value = {"/province"})
    public ResponseEntity<?> getAll() throws Exception {

        return new ResponseEntity<>(provinceService.getAll(), HttpStatus.OK);
    }
}

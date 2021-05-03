package com.example.football.api.controllers;

import com.example.football.models.Price;
import com.example.football.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/football")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/price/{id}")
    public ResponseEntity<Price> get(@PathVariable Integer id) {
        try {
            Price price = priceService.getPriceById(id);
            return new ResponseEntity<Price>(price, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
        }
    }
}

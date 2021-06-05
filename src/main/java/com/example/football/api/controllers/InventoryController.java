package com.example.football.api.controllers;

import com.example.football.repositories.InventoryRepository;
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
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory/getSLPitch/{pitch_id}")
    public ResponseEntity<Integer> getSLPitch(@PathVariable Integer pitch_id) {
        try {
            return new ResponseEntity<Integer>(inventoryRepository.getSLPitch(pitch_id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
    }
}

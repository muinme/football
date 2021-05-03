package com.example.football.api.controllers;

import com.example.football.models.Price;
import com.example.football.models.TimeSlotPitch;
import com.example.football.services.PriceService;
import com.example.football.services.TimeSlotPitchService;
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
public class TimeSlotPitchController {
    @Autowired
    private TimeSlotPitchService timeSlotPitchService;

    @GetMapping("/timeSlotPitch/{id}")
    public ResponseEntity<TimeSlotPitch> get(@PathVariable Integer id) {
        try {
            TimeSlotPitch timeSlotPitch = timeSlotPitchService.getTimeById(id);
            return new ResponseEntity<TimeSlotPitch>(timeSlotPitch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<TimeSlotPitch>(HttpStatus.NOT_FOUND);
        }
    }
}

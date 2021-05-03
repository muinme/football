package com.example.football.api.controllers;

import com.example.football.models.DayOfWeek;
import com.example.football.models.TimeSlotPitch;
import com.example.football.services.DayOfWeekService;
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
public class DayOfWeekController {
    @Autowired
    private DayOfWeekService dayOfWeekService;

    @GetMapping("/dayOfWeek/{id}")
    public ResponseEntity<DayOfWeek> get(@PathVariable Integer id) {
        try {
            DayOfWeek dayOfWeek = dayOfWeekService.getDayById(id);
            return new ResponseEntity<DayOfWeek>(dayOfWeek, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DayOfWeek>(HttpStatus.NOT_FOUND);
        }
    }
}

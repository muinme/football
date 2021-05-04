package com.example.football.api.controllers;

import com.example.football.models.PieInfo;
import com.example.football.models.Price;
import com.example.football.models.TimeSlotPitch;
import com.example.football.services.PriceService;
import com.example.football.services.TimeSlotPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @RequestMapping(value = {"/timeSlotPitch/getAllInfo"}, method = RequestMethod.GET)
    public ResponseEntity<List<PieInfo>> listPieInfoTimeSlotPitchService() {
        return new ResponseEntity<List<PieInfo>>(this.timeSlotPitchService.pieInfoList(), HttpStatus.OK);
    }
}

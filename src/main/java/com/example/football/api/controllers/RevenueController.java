package com.example.football.api.controllers;


import com.example.football.services.ProvinceService;
import com.example.football.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/football")
public class RevenueController {

    @Autowired
    RevenueService revenueService;

    @GetMapping(value = {"/revenue/{pitch_id}/{number_pitch_id}"})
    public ResponseEntity<?> getRevenue(@PathVariable Integer pitch_id, @PathVariable Integer number_pitch_id) throws Exception {

        return new ResponseEntity<>(revenueService.findRevenue(pitch_id, number_pitch_id), HttpStatus.OK);
    }

    @PostMapping(value = {"/sumRevenue/{pitch_id}/{number_pitch_id}"})
    public ResponseEntity<?> sumRevenue(@PathVariable Integer pitch_id, @PathVariable Integer number_pitch_id) throws Exception {

        return new ResponseEntity<>(revenueService.sumRevenue(pitch_id, number_pitch_id), HttpStatus.OK);
    }

    @GetMapping(value = {"/sumRevenuePitch/{pitch_id}"})
    public Integer sumRevenuePitch(@PathVariable Integer pitch_id) {
        return revenueService.sumRevenuePitch(pitch_id);
    }

    @GetMapping(value = {"/sumRevenueAll/{pitch_id}"})
    public Integer sumRevenueAll(@PathVariable Integer pitch_id) {
        return revenueService.sumRevenueAll(pitch_id);
    }

}

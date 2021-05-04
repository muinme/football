package com.example.football.api.controllers;

import com.example.football.models.HistoryRental;
import com.example.football.models.PieInfo;
import com.example.football.services.HistoryMatchService;
import com.example.football.services.HistoryRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/football")
public class HistoryRentalController {
    @Autowired
    private HistoryRentalService historyRentalService;

    @RequestMapping(value = {"/historyRental1/create/{request_pitch_id}"}, method = RequestMethod.POST)
    public HistoryRental createHistoryMatch1(@PathVariable Integer request_pitch_id) {
        return historyRentalService.saveHistoryRental1(request_pitch_id);
    }

    @RequestMapping(value = {"/historyRental2/create/{request_pitch_id}"}, method = RequestMethod.POST)
    public HistoryRental createHistoryMatch2(@PathVariable Integer request_pitch_id) {
        return historyRentalService.saveHistoryRental2(request_pitch_id);
    }
    @RequestMapping(value = {"/historyRental/getAllInfo"}, method = RequestMethod.GET)
    public ResponseEntity<List<PieInfo>> listPieInfoHistoryRental() {
        return new ResponseEntity<List<PieInfo>>(this.historyRentalService.pieInfoList(), HttpStatus.OK);
    }
}

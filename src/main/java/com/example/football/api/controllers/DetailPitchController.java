package com.example.football.api.controllers;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.DetailPitch;
import com.example.football.models.Pitch;
import com.example.football.models.PostMatchTeam;
import com.example.football.services.AuthenticationService;
import com.example.football.services.DetailPitchService;
import com.example.football.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/football")
public class DetailPitchController {
    @Autowired
    private DetailPitchService detailPitchService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @GetMapping("/detail_pitch")
    public List<DetailPitch> list() {
        return detailPitchService.listAllDetailPitch();
    }

    @GetMapping("/detail_pitch/{pitch_id}/{timeslot_id}/{day_id}")
    public List<DetailPitch> getListDetailPitch(@PathVariable Integer pitch_id, @PathVariable Integer timeslot_id,  @PathVariable Integer day_id) {

        return detailPitchService.getListDetailPitch(pitch_id, timeslot_id, day_id);

    }
    @RequestMapping(value = {"/detailPitch/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<DetailPitch> getDetailPitch(@PathVariable Integer id) {
        try {
            DetailPitch detailPitch = detailPitchService.getByIdDetailPitch(id);
            return new ResponseEntity<DetailPitch>(detailPitch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DetailPitch>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/detailPitch/getStatusHire/{pitch_id}/{number_pitch_id}"}, method = RequestMethod.GET)
    public List<String> getDetailPitch(@PathVariable Integer pitch_id, @PathVariable Integer number_pitch_id) {
      return detailPitchService.getListStatusHire(pitch_id, number_pitch_id);
    }

    @PostMapping("/detail_pitch/updateStatus/{pitch_id}/{timeslot_id}/{day_id}/{number_pitch_id}/{status_hire}")
    public ResponseEntity<?> updateDetailPitchStatusOfTime(@PathVariable Integer pitch_id, @PathVariable Integer timeslot_id, @PathVariable Integer day_id, @PathVariable Integer number_pitch_id ,@PathVariable String status_hire) {
        try {
            detailPitchService.updateDetailPitchOfTime(pitch_id, timeslot_id, day_id, number_pitch_id, status_hire);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception internalError)
        {
            internalError.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/detail_pitch/update/{id}")
//    public ResponseEntity<?> update(@RequestBody DetailPitch detailPitch, @PathVariable Integer id){
//        try {
//            DetailPitch existDetailPitch = detailPitchService.getByIdDetailPitch(id);
//            try{
//                detailPitchService.saveDetailPitch(detailPitch);
//                return new ResponseEntity<>(HttpStatus.OK);
//            }catch (Exception internalError)
//            {
//                internalError.printStackTrace();
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/detail_pitch/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            detailPitchService.deleteDetailPitch(id);
        } catch (NoSuchElementException e) {

        }
    }

    @GetMapping("/detail_pitch/getInfo/{request_pitch_id}")
    public ResponseEntity<?> getInfo(@PathVariable Integer request_pitch_id) {
        try {
            return new ResponseEntity<DetailPitch>(detailPitchService.getInfo(request_pitch_id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DetailPitch>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail_pitch/getSLPitch/{pitch_id}")
    public Integer getSLPitch(@PathVariable Integer pitch_id){
        return detailPitchService.getSLPitch(pitch_id);
    }
}

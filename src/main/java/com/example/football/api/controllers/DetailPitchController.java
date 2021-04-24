package com.example.football.api.controllers;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.DetailPitch;
import com.example.football.services.AuthenticationService;
import com.example.football.services.DetailPitchService;
import com.example.football.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/detail_pitch/{id}")
    public ResponseEntity<DetailPitch> get(@PathVariable Integer id) {
        try {
            DetailPitch detailPitch = detailPitchService.getByIdDetailPitch(id);
            return new ResponseEntity<DetailPitch>(detailPitch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<DetailPitch>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/detail_pitch/create")
    public DetailPitch create(@RequestBody DetailPitch detailPitch) {
        return detailPitchService.createDetailPitch(detailPitch);
    }

    @PostMapping("/detail_pitch/update/{id}")
    public ResponseEntity<?> update(@RequestBody DetailPitch detailPitch, @PathVariable Integer id){
        try {
            DetailPitch existDetailPitch = detailPitchService.getByIdDetailPitch(id);
            try{
                detailPitchService.saveDetailPitch(detailPitch);
                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception internalError)
            {
                internalError.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/detail_pitch/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            detailPitchService.deleteDetailPitch(id);
        } catch (NoSuchElementException e) {

        }
    }

}

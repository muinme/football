package com.example.football.api.controllers;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.services.AuthenticationService;
import com.example.football.services.Impl.UserServiceImpl;
import com.example.football.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/football")
public class PitchController {
    @Autowired
    private PitchService pitchService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @GetMapping("/pitch")
    public List<Pitch> list() {
        return pitchService.listAllPitch();
    }

    @GetMapping("/pitch/{id}")
    public ResponseEntity<Pitch> get(@PathVariable Integer id) {
        try {
            Pitch pitch = pitchService.getByIdPitch(id);
            return new ResponseEntity<Pitch>(pitch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Pitch>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pitch/create")
    public Pitch create(@RequestBody Pitch pitch) {
        return pitchService.createPitch(pitch);
    }

    @PostMapping("/pitch/update/{id}")
    public ResponseEntity<?> update(@RequestBody Pitch pitch, @PathVariable Integer id){
        try {
            Pitch existPitch = pitchService.getByIdPitch(id);
            try{
                pitchService.savePitch(pitch);
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

    @PostMapping("/pitch/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            pitchService.deletePitch(id);
        } catch (NoSuchElementException e) {

        }
    }

}
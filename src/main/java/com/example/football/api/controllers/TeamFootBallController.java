package com.example.football.api.controllers;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.TeamFootBall;
import com.example.football.services.AuthenticationService;
import com.example.football.services.Impl.UserServiceImpl;
import com.example.football.services.TeamFootBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/football")
public class TeamFootBallController {
    @Autowired
    private TeamFootBallService teamFootBallService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @GetMapping("/teamFootBall/getAll")
    public ResponseEntity<?> list() throws Exception {
        return new ResponseEntity<>(teamFootBallService.listAllTeamFootBall(), HttpStatus.OK);
    }
    @GetMapping("/teamFootBall/{id}")
    public ResponseEntity<TeamFootBall> get(@PathVariable Integer id) {
        try {
            TeamFootBall teamFootBall = teamFootBallService.getByIdTeamFootBall(id);
            return new ResponseEntity<TeamFootBall>(teamFootBall, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<TeamFootBall>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teamFootBall/create")
    public TeamFootBall create(@RequestBody TeamFootBall teamFootBall) {
        return teamFootBallService.createTeamFootBall(teamFootBall);
    }

    @PostMapping("/teamFootBall/update/{id}")
    public ResponseEntity<?> update(@RequestBody TeamFootBall teamFootBall, @PathVariable Integer id){
        try {
            TeamFootBall existTeamFootBall = teamFootBallService.getByIdTeamFootBall(id);
            try{
                teamFootBallService.saveTeamFootBall(teamFootBall);
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

    @PostMapping("/teamFootBall/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            teamFootBallService.deleteTeamFootBall(id);
        } catch (NoSuchElementException e) {

        }
    }
}

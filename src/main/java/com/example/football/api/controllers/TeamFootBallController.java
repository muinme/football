package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.models.TeamFootBall;
import com.example.football.services.AuthenticationService;
import com.example.football.services.Impl.UserServiceImpl;
import com.example.football.services.TeamFootBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/football")
public class TeamFootBallController {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private TeamFootBallService teamFootBallService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  CookieUtil cookieUtil;

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

    @GetMapping("/teamFootBall/{tt}/{qh}")
    public ResponseEntity<?> getByAddress(@PathVariable String tt, @PathVariable String qh) {
        try {
            return new ResponseEntity<>(teamFootBallService.getTeamByAddress(tt, qh), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teamFootBall/actionTime")
    public ResponseEntity<?> getByActionTime() {
        try {
            return new ResponseEntity<>(teamFootBallService.findActionTime(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teamFootBall/getByActionTimeAndLevel/{actionTime}/{level}")
    public ResponseEntity<?> getByActionTimeAndLevel(@PathVariable String actionTime, @PathVariable String level) {
        try {
            return new ResponseEntity<>(teamFootBallService .getByActionTimeAndLevel(actionTime, level), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/teamFootBall/Username"}, method = RequestMethod.GET)
    public List<TeamFootBall> getTeam(HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        return teamFootBallService.getTeamByUsername(username);
    }

    @PostMapping("/teamFootBall/create")
    public TeamFootBall create(@RequestBody TeamFootBall teamFootBall, HttpServletRequest httpServletRequest) {
        String jwt = cookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(null == jwt) {
            System.out.println("Chua login | khong the lay token trong cookie");
            // TODO return;
        }
        // kiem tra token duoc luu trong redis xem co hay khong
        // TODO
        // Neu dung thi tiep tuc
        System.out.println(teamFootBall);
        String username = jwtUtil.getUsernameFromToken(jwt);
        System.out.println("username in cookie = " + username);
        return teamFootBallService.createTeamFootBall(teamFootBall, username);
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

    @GetMapping("teamFootBall/getNameTeam/{football_id}")
    public ResponseEntity<?> getNamePitch(@PathVariable Integer football_id) {
        try {
            return new ResponseEntity<TeamFootBall>(teamFootBallService.findNameTeam(football_id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<TeamFootBall>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teamFootBall/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            teamFootBallService.deleteTeamFootBall(id);
            return new ResponseEntity<String>( "Xóa thành công", HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Xóa thất bại",HttpStatus.NOT_FOUND);
        }
    }
}

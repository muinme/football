package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.Pitch;
import com.example.football.services.Impl.UserServiceImpl;
import com.example.football.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/football")
public class PitchController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private PitchService pitchService;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @GetMapping("/pitch/getAll")
    public ResponseEntity<?> list() throws Exception {
        return new ResponseEntity<>(pitchService.listAllPitch(), HttpStatus.OK);
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
    @RequestMapping(value = {"/pitch/Username"}, method = RequestMethod.GET)
    public List<Pitch> getPitch(HttpServletRequest httpServletRequest) {
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
        return pitchService.getPitchByUsername(username);
    }

    @PostMapping("/pitch/create")
    public Pitch create(@RequestBody Pitch pitch, HttpServletRequest httpServletRequest) {
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
        return pitchService.createPitch(pitch, username);
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
package com.example.football.api.controllers;

import com.example.football.infrastructure.security.CookieUtil;
import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.DetailPitch;
import com.example.football.models.Pitch;
import com.example.football.models.PostMatchTeam;
import com.example.football.services.PostMatchTeamService;
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
public class PostMatchTeamController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";

    @Autowired
    private PostMatchTeamService postMatchTeamService;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = {"/postMatchTeam/getAll"}, method = RequestMethod.GET)
    public List<PostMatchTeam> listPostMatchTeam() {
        return postMatchTeamService.listAllPostMatchTeam();
    }

    @RequestMapping(value = {"/postMatchTeam/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<PostMatchTeam> getPostMatchTeam(@PathVariable Integer id) {
        try {
            PostMatchTeam postMatchTeam = postMatchTeamService.getByIdPostMatchTeam(id);
            return new ResponseEntity<PostMatchTeam>(postMatchTeam, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<PostMatchTeam>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/postMatchTeam/Username"}, method = RequestMethod.GET)
    public List<PostMatchTeam> getPostByUsername(HttpServletRequest httpServletRequest) {
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
        return postMatchTeamService.listFindByUsername(username);
    }
    @RequestMapping(value = {"/postMatchTeam/getByFootBallId/{football_id}"}, method = RequestMethod.GET)
    public List<PostMatchTeam> getPostByFootBallId( @PathVariable Integer football_id) {
        return postMatchTeamService.listFindByFootBallId(football_id);
    }

    @RequestMapping(value = {"/postMatchTeam/create/{nameTeamFootBall}"}, method = RequestMethod.POST)
    public PostMatchTeam create(@RequestBody PostMatchTeam postMatchTeam, @PathVariable String nameTeamFootBall) {
        return postMatchTeamService.createPostMatchTeam(postMatchTeam, nameTeamFootBall);
    }

    @RequestMapping(value = {"/postMatchTeam/update/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody PostMatchTeam postMatchTeam, @PathVariable Integer id){
        try {
            PostMatchTeam existPostMatchTeam = postMatchTeamService.getByIdPostMatchTeam(id);
            try{
                postMatchTeamService.savePostMatchTeam(postMatchTeam);
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

    @GetMapping("/postMatchTeam/actionTime")
    public ResponseEntity<?> getByPlayTime() {
        try {
            return new ResponseEntity<>(postMatchTeamService.findPlayTime(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/postMatchTeam/{tt}/{qh}")
    public ResponseEntity<?> getByAddress(@PathVariable String tt, @PathVariable String qh) {
        try {
            return new ResponseEntity<>(postMatchTeamService.getPostByAddress(tt, qh), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/postMatchTeam/getByActionTimeAndLevel/{actionTime}/{level}")
    public ResponseEntity<?> getByActionTimeAndLevel(@PathVariable String actionTime, @PathVariable String level) {
        try {
            return new ResponseEntity<>(postMatchTeamService .getByPlayTimeAndLevelWant(actionTime, level), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"/postMatchTeam/delete/{id}"}, method = RequestMethod.POST)
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
            try{
                return new ResponseEntity<>(postMatchTeamService.deletePostMatchTeam(id),HttpStatus.OK);
            }
            catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @RequestMapping(value = {"/postMatchTeam/getSlWaitPost/{football_id}"}, method = RequestMethod.GET)
    public Integer getSlWaitPost(@PathVariable Integer football_id) {
        return postMatchTeamService.getSlWaitPost(football_id);
    }

    @RequestMapping(value = {"/postMatchTeam/getSlWaitPostDel/{football_id}"}, method = RequestMethod.GET)
    public Integer getSlWaitPostDel(@PathVariable Integer football_id) {
        return postMatchTeamService.getSlWaitPostDel(football_id);
    }

    @GetMapping("/postMatchTeam/getInfo/{request_match_id}")
    public ResponseEntity<?> getInfo(@PathVariable Integer request_match_id) {
        try {
            return new ResponseEntity<PostMatchTeam>(postMatchTeamService.getInfo(request_match_id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<PostMatchTeam>(HttpStatus.NOT_FOUND);
        }
    }
}

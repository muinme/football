package com.example.football.api.controllers;

import com.example.football.models.DetailPitch;
import com.example.football.models.PostMatchTeam;
import com.example.football.services.PostMatchTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

public class PostMatchTeamController {
    @Autowired
    private PostMatchTeamService postMatchTeamService;

    @RequestMapping(value = {"/postMatchTeam/getAll"}, method = RequestMethod.GET)
    public List<PostMatchTeam> list() {
        return postMatchTeamService.listAllPostMatchTeam();
    }

    @RequestMapping(value = {"/postMatchTeam/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<PostMatchTeam> get(@PathVariable Integer id) {
        try {
            PostMatchTeam postMatchTeam = postMatchTeamService.getByIdPostMatchTeam(id);
            return new ResponseEntity<PostMatchTeam>(postMatchTeam, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<PostMatchTeam>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = {"/postMatchTeam/create"}, method = RequestMethod.POST)
    public PostMatchTeam create(@RequestBody PostMatchTeam postMatchTeam) {
        return postMatchTeamService.createPostMatchTeam(postMatchTeam);
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
    @RequestMapping(value = {"/postMatchTeam/delete/{id}"}, method = RequestMethod.POST)
    public void delete(@PathVariable Integer id) {
        try {
            postMatchTeamService.deletePostMatchTeam(id);
        } catch (NoSuchElementException e) {

        }
    }
}

package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teamfootballs")
public class TeamFootBall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String logo;
    private String image;
    private int agemin;
    private int agemax;
    private String homeyard;
    private String actiontime;
    private String level;
    private String introduce;
    private java.util.Date created;
}
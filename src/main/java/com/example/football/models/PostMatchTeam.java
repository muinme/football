package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wait_match_team")
public class PostMatchTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer football_id;
    private String playtime;
    private String home_guest;
    private String nameyard;
    private String category;
    private String levelwant;
    private String note;
    private java.util.Date created;
}

package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pitchs")
public class Pitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String introduce;
    private String type;
    private String address;
    private String googlemap;
    private String image;
    private java.util.Date created;
}


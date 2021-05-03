package com.example.football.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "price_pitch")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
}

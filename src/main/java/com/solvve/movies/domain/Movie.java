package com.solvve.movies.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String country;
    private String genre;
    private int year;
    private int duration;
    private String description;
}

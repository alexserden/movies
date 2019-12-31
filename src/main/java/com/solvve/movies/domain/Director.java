package com.solvve.movies.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Director {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies;
}

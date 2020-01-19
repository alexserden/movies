package com.solvve.movies.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String country;
    private String genre;
    private Integer year;
    private Integer duration;
    private String description;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.EAGER)
    @JoinTable(name = "director_movies",
            joinColumns = @JoinColumn(name = "directors_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id")
    )
    private List<Director> directors;
}

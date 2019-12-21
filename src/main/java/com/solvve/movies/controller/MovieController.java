package com.solvve.movies.controller;

import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    public MovieReadDTO getMovie(@PathVariable UUID id) {
        return movieService.getMovie(id);
    }
}

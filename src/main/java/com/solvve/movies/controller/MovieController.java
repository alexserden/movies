package com.solvve.movies.controller;

import com.solvve.movies.dto.MovieCreateDTO;
import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    public MovieReadDTO getMovie(@PathVariable @NotNull UUID id) {
        MovieReadDTO m = movieService.getMovie(id);
        return m;
    }

    @PostMapping
    public MovieReadDTO createMovie(@RequestBody MovieCreateDTO movieCreateDTO){
      return movieService.createMovie(movieCreateDTO);
    }
}

package com.solvve.movies.service;

import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieCreateDTO;
import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.exception.EntityNotFoundException;
import com.solvve.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieReadDTO getMovie(UUID id) {
        return MovieReadDTO.convertMovieToMovieReadDTO(movieRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Movie.class, id);
        }));
    }

    public MovieReadDTO createMovie(MovieCreateDTO movieCreateDTO) {
       Movie m = movieRepository.save(MovieCreateDTO.convertMovieCreateDTOToMovie(movieCreateDTO));
        return MovieReadDTO.convertMovieToMovieReadDTO(m);
    }
}

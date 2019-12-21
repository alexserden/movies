package com.solvve.movies.service;

import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieReadDTO getMovie(UUID id){
     return MovieReadDTO.convertMovieToMovieReadDTO(movieRepository.findById(id).get());
    }
}

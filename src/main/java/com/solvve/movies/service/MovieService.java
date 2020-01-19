package com.solvve.movies.service;

import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieCreateDTO;
import com.solvve.movies.dto.MoviePatchDTO;
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
        return MovieReadDTO.convertMovieToMovieReadDTO(getMovieRequired(id));
    }

    public MovieReadDTO createMovie(MovieCreateDTO movieCreateDTO) {
       Movie m = movieRepository.save(MovieCreateDTO.convertMovieCreateDTOToMovie(movieCreateDTO));
        return MovieReadDTO.convertMovieToMovieReadDTO(m);
    }

    public MovieReadDTO patchMovie(UUID id, MoviePatchDTO patchDTO) {
        Movie m = getMovieRequired(id);
        if(patchDTO.getTitle() != null) {
            m.setTitle(patchDTO.getTitle());
        }
        if(patchDTO.getCountry() != null) {
            m.setCountry(patchDTO.getCountry());
        }
        if(patchDTO.getGenre() != null) {
            m.setGenre(patchDTO.getGenre());
        }
        if(patchDTO.getYear() != null) {
            m.setYear(patchDTO.getYear());
        }
        if(patchDTO.getDuration() != null) {
            m.setDuration(patchDTO.getDuration());
        }
        if(patchDTO.getDescription() != null) {
            m.setDescription(patchDTO.getDescription());
        }
        if(patchDTO.getDirectors() != null){
            m.setDirectors(patchDTO.getDirectors());
        }
         m = movieRepository.save(m);
        return MovieReadDTO.convertMovieToMovieReadDTO(m);
    }

    public void deleteMovie(UUID id) {
        movieRepository.delete(getMovieRequired(id));
    }

    private Movie getMovieRequired(UUID id) {
       return movieRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Movie.class, id);
        });
    }
}

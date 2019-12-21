package com.solvve.movies.dto;

import com.solvve.movies.domain.Movie;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.util.UUID;

@Data
public class MovieReadDTO {

    private UUID id;
    private String title;
    private String country;
    private String genre;
    private int year;
    private int duration;
    private String description;

    public static MovieReadDTO convertMovieToMovieReadDTO(Movie movie){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movie, MovieReadDTO.class);
    }
}

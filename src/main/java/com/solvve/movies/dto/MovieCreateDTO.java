package com.solvve.movies.dto;

import com.solvve.movies.domain.Movie;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MovieCreateDTO {

    private String title;
    private String country;
    private String genre;
    private int year;
    private int duration;
    private String description;

    public static Movie convertMovieCreateDTOToMovie(MovieCreateDTO movieCreateDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieCreateDTO, Movie.class);
    }
}

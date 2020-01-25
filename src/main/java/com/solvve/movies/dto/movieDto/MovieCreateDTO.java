package com.solvve.movies.dto.movieDto;

import com.solvve.movies.domain.Director;
import com.solvve.movies.domain.Movie;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class MovieCreateDTO {

    private String title;
    private String country;
    private String genre;
    private Integer year;
    private Integer duration;
    private String description;
    private List<Director> directors;


    public static Movie convertMovieCreateDTOToMovie(MovieCreateDTO movieCreateDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(movieCreateDTO, Movie.class);
    }
}

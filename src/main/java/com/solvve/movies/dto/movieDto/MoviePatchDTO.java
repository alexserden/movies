package com.solvve.movies.dto.movieDto;

import com.solvve.movies.domain.Director;
import com.solvve.movies.domain.Movie;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class MoviePatchDTO {

    private String title;
    private String country;
    private String genre;
    private Integer year;
    private Integer duration;
    private String description;
    private List<Director> directors;

}

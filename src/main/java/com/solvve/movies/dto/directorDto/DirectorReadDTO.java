package com.solvve.movies.dto.directorDto;

import com.solvve.movies.domain.Director;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Data
public class DirectorReadDTO {

    private UUID id;
    private String name;

    public static DirectorReadDTO convertMovieToMovieReadDTO(Director director){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(director, DirectorReadDTO.class);
    }
}

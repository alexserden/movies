package com.solvve.movies.service;

import com.solvve.movies.dto.directorDto.DirectorReadDTO;
import com.solvve.movies.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    public DirectorReadDTO getDirector(UUID id) {
        return  DirectorReadDTO.convertMovieToMovieReadDTO(directorRepository.findById(id).get());
    }
}

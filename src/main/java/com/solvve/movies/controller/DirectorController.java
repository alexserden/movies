package com.solvve.movies.controller;

import com.solvve.movies.dto.directorDto.DirectorReadDTO;
import com.solvve.movies.service.DirectorService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @GetMapping("/{id}")
    public DirectorReadDTO getMovie(@PathVariable @NotNull UUID id) {
        DirectorReadDTO d = directorService.getDirector(id);
        return d;
    }
}

package com.solvve.movies.service;

import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieCreateDTO;
import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.exception.EntityNotFoundException;
import com.solvve.movies.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from movie", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MovieServiceTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @Test
    public void testGetMovie(){
        Movie m = new Movie();
        m.setTitle("One at home");
        m.setCountry("USA");
        m.setGenre("Comedy");
        m.setYear(1992);
        m.setDuration(2345);
        m.setDescription("This comedy for family");
        m = movieRepository.save(m);

        MovieReadDTO readDTO = movieService.getMovie(m.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(m);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetMovieWrongId(){
        movieService.getMovie(UUID.randomUUID());
    }

    @Test
    public void  testCreateMovie(){
        MovieCreateDTO create = new MovieCreateDTO();
        create.setTitle("One at home");
        create.setCountry("USA");
        create.setGenre("Comedy");
        create.setYear(1992);
        create.setDuration(2345);
        create.setDescription("This comedy for family");

        MovieReadDTO read = movieService.createMovie(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Movie m = movieRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(m);
    }
}


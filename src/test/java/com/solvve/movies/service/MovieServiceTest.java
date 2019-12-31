package com.solvve.movies.service;

import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.exception.EntityNotFoundException;
import com.solvve.movies.repository.MovieRepository;
import org.assertj.core.api.Assertions;
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
}


package com.solvve.movies.service;

import com.solvve.movies.domain.Director;
import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieCreateDTO;
import com.solvve.movies.dto.MoviePatchDTO;
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

import java.util.ArrayList;
import java.util.List;
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
        Movie m =  createMovie();
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

    @Test
    public void testPatchMovie() {
        Movie m =  createMovie();
        m = movieRepository.save(m);

        MoviePatchDTO moviePatchDTO = new MoviePatchDTO();
        moviePatchDTO.setTitle("Second at home");
        moviePatchDTO.setCountry("UK");
        moviePatchDTO.setGenre("Drama");
        moviePatchDTO.setYear(1998);
        moviePatchDTO.setDuration(234345);
        moviePatchDTO.setDescription("This drama");
        List<Director> directors = new ArrayList<>();
        moviePatchDTO.setDirectors(directors);
        MovieReadDTO readDTO = movieService.patchMovie(m.getId(),moviePatchDTO);

        Assertions.assertThat(moviePatchDTO).isEqualToComparingFieldByField(readDTO);

        m = movieRepository.findById(readDTO.getId()).get();
      //  Assertions.assertThat(m).isEqualToComparingFieldByField(readDTO);
    }

    @Test
    public void testPatchMovieEmptyPatch() {
        Movie m = createMovie();
        m = movieRepository.save(m);

        MoviePatchDTO patch = new MoviePatchDTO();
        MovieReadDTO read = movieService.patchMovie(m.getId(), patch);

        Assert.assertNotNull(read.getTitle());
        Assert.assertNotNull(read.getCountry());
        Assert.assertNotNull(read.getGenre());
        Assert.assertNotNull(read.getYear());
        Assert.assertNotNull(read.getDuration());
        Assert.assertNotNull(read.getDescription());
        Assert.assertNotNull(read.getDirectors());

        Movie movieAfterUpdate = movieRepository.findById(read.getId()).get();

        Assert.assertNotNull(movieAfterUpdate.getTitle());
        Assert.assertNotNull(movieAfterUpdate.getCountry());
        Assert.assertNotNull(movieAfterUpdate.getGenre());
        Assert.assertNotNull(movieAfterUpdate.getYear());
        Assert.assertNotNull(movieAfterUpdate.getDuration());
        Assert.assertNotNull(movieAfterUpdate.getDescription());
        Assert.assertNotNull(movieAfterUpdate.getDirectors());

      // Assertions.assertThat(m).isEqualToComparingFieldByField(movieAfterUpdate);
    }

    @Test
    public void testDeleteMovie() {
        Movie m = createMovie();

        movieService.deleteMovie(m.getId());
        Assert.assertFalse(movieRepository.existsById(m.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteMovieNotFound() {
        movieService.deleteMovie(UUID.randomUUID());
    }
    private Movie createMovie() {
        Movie m = new Movie();
        m.setTitle("One at home");
        m.setCountry("USA");
        m.setGenre("Comedy");
        m.setYear(1992);
        m.setDuration(2345);
        m.setDescription("This comedy for family");
        List<Director> directors = new ArrayList<>();
        m.setDirectors(directors);
        return m;
    }
}


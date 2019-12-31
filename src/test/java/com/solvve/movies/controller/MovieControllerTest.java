package com.solvve.movies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvve.movies.domain.Movie;
import com.solvve.movies.dto.MovieReadDTO;
import com.solvve.movies.exception.EntityNotFoundException;
import com.solvve.movies.service.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    @Test
    public void testGetMovie() throws Exception {

        MovieReadDTO movieReadDTO = new MovieReadDTO();
        movieReadDTO.setId(UUID.randomUUID());
        movieReadDTO.setTitle("One at home");
        movieReadDTO.setCountry("USA");
        movieReadDTO.setGenre("comedy");
        movieReadDTO.setYear(1992);
        movieReadDTO.setDuration(13535);
        movieReadDTO.setDescription("This comedy for family");

        Mockito.when(movieService.getMovie(movieReadDTO.getId())).thenReturn(movieReadDTO);

        String resultJson = mvc.perform(get("/api/v1/movies/{id}", movieReadDTO.getId()))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        MovieReadDTO actualMovie = objectMapper.readValue(resultJson,MovieReadDTO.class);
        Assertions.assertThat(actualMovie).isEqualToComparingFieldByField(movieReadDTO);
        Mockito.verify(movieService).getMovie(movieReadDTO.getId());
    }

    @Test
    public void testGetMovieWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Movie.class, wrongId);
        Mockito.when(movieService.getMovie(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/movies/{id}", wrongId))
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

        Assert.assertTrue(resultJson.contains(exception.getMessage()));
    }

    @Test
    public void testGetMovieWrongFormatId() throws Exception {
        String wrongFormatId = "123";
        mvc.perform(get("/api/v1/movies/{id}", wrongFormatId))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }
}

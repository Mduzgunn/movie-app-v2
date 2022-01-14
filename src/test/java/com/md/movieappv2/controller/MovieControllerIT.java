package com.md.movieappv2.controller;

import com.md.movieappv2.IntegrationTestSupport;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;
import java.time.Clock;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class MovieControllerIT extends IntegrationTestSupport {

    private final String url = "/v2/movie/";

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @BeforeEach
    public void setup() {
        Clock clock = mock(Clock.class);

        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testGetAllMovies_shouldReturnEmptyList() throws Exception {

        this.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Movie> movieFromDb = movieRepository.findAll();
        assertEquals(0, movieFromDb.size());
    }

    @Test
    public void testGetAllMovies_shouldReturnMovieDtoList() throws Exception {
        Actor actor =actorRepository.save(new Actor("firstname","lastname"));
        Director director =directorRepository.save(new Director("firstname","lastname"));
        Publisher publisher =publisherRepository.save(new Publisher("lastname"));
        movieRepository.save(new Movie("username", 2021,"desc",111,"media",
                true, Collections.singletonList(Genre.COMEDY), Collections.singletonList(actor),director,publisher));

        this.mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Movie> movieFromDb = movieRepository.findAll();
        assertEquals(1, movieFromDb.size());
    }


    @Test
    @Transactional
    public void testGetMovieById_whenValidId_shouldReturnMovieDto() throws Exception {
        Actor actor =actorRepository.save(new Actor("firstname","lastname"));
        Director director =directorRepository.save(new Director("firstname","lastname"));
        Publisher publisher =publisherRepository.save(new Publisher("lastname"));
        Movie movie= movieRepository.save(new Movie("username", 2021,"desc",111,"media",
                true, Collections.singletonList(Genre.COMEDY), Collections.singletonList(actor),director,publisher));

        this.mockMvc.perform(get(url + movie.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(movie.getId())));

        Movie movieFromDb = movieRepository.findById(movie.getId()).get();
        assertEquals(movie, movieFromDb);
    }

    @Test
    public void testGetMovieById_whenInvalidId_shouldReturnNotFoundException() throws Exception {

        this.mockMvc.perform(get(url + "invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateMovie_whenCreateMovieRequestIsInvalid_shouldNotCreateMovieAndReturn400BadRequest() throws Exception {

        Actor actor =actorRepository.save(new Actor("firstname","lastname"));
        Director director =directorRepository.save(new Director("firstname","lastname"));
        Publisher publisher =publisherRepository.save(new Publisher("firstname"));
        CreateMovieRequest createMovieRequest = new CreateMovieRequest(
                "", 1999,"",111,"media",
                true, Collections.singletonList(Genre.COMEDY), Collections.singletonList(List.of(actor).toString()),
                director.getId(),publisher.toString()
        );
        this.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(createMovieRequest)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.releaseYear", notNullValue()))
                .andExpect(jsonPath("$.description", notNullValue()));

        List<Movie> movieFromDb = movieRepository.findAll();
        assertEquals(0, movieFromDb.size());
    }







}

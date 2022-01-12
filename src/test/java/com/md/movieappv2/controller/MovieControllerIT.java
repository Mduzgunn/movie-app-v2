package com.md.movieappv2.controller;

import com.md.movieappv2.IntegrationTestSupport;
import com.md.movieappv2.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.Clock;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}

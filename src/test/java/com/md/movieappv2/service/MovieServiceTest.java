package com.md.movieappv2.service;

import com.md.movieappv2.TestSupport;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.*;
import com.md.movieappv2.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MovieServiceTest extends TestSupport {

    private MovieRepository movieRepository;
    private MovieDtoConverter movieDtoConverter;
    private ActorService actorService;
    private DirectorService directorService;
    private PublisherService publisherService;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieDtoConverter = mock(MovieDtoConverter.class);
        actorService = mock(ActorService.class);
        directorService = mock(DirectorService.class);
        publisherService = mock(PublisherService.class);


        movieService = new MovieService(actorService, directorService, publisherService,
                movieRepository, movieDtoConverter);
    }

    @Test
    void testGetMovieById_whenIdExist_shouldReturnMovieDto() {
        Movie movie = generateMovie();
        MovieDto movieDto = generateMovieDto();

        when(movieRepository.findById("id")).thenReturn(Optional.of(movie));
        when(movieDtoConverter.convert(movie)).thenReturn(movieDto);

        MovieDto result = movieService.getMovieById("id");

        assertEquals(movieDto, result);

        verify(movieRepository).findById("id");
        verify(movieDtoConverter).convert(movie);
    }

    @Test
    void testGetMovieById_whenIdNotExist_shouldThrowNotFoundException() {
        when(movieRepository.findById("id")).thenThrow(MovieNotFoundException.class);

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById("id"));

        verify(movieRepository).findById("id");
        verifyNoInteractions(movieDtoConverter);
    }

    @Test
    void testGetMovieDtoList_shouldReturnListOfMovieDto() {
        List<Movie> movieList = generateMovieList();
        List<MovieDto> movieDtoList = generateMovieDtoList();

        when(movieRepository.findAll()).thenReturn(movieList);
        when(movieDtoConverter.convertToMovieDtoList(movieList)).thenReturn(movieDtoList);

        List<MovieDto> result = movieService.getAllMovieDtoList();

        assertEquals(movieDtoList, result);

        verify(movieRepository).findAll();
        verify(movieDtoConverter).convertToMovieDtoList(movieList);
    }

    @Test
    void testCreateMovie_whenCreateMovieRequest_shouldReturnMovieDto() {
        CreateMovieRequest movieRequest = generateCreateMovieRequest();
        Movie movie = generateMovie();
        MovieDto movieDto = generateMovieDto();
        Actor actor = generateActor();
        Director director = generateDirector();
        Publisher publisher = generatePublisher();

        when(directorService.findDirectorById("id")).thenReturn(director);
        when(publisherService.findPublisherById("id")).thenReturn(publisher);
        when(actorService.findActorById("id")).thenReturn(actor);
        when(movieDtoConverter.convert(movieRepository.save(movie))).thenReturn(movieDto);

        MovieDto result = movieService.createMovie(movieRequest);

        assertEquals(movieDto, result);

        verify(directorService).findDirectorById("directorId");
        verify(publisherService).findPublisherById("publisherId");
        verify(actorService).getActorListByIdList(Collections.singletonList("actorId1"));
        verify(movieDtoConverter).convert(movieRepository.save(movie));
    }

    @Test
    void testCreateMovie_whenPublisherIdNotExist_shouldThrowRuntimeException() {
        CreateMovieRequest movieRequest = generateCreateMovieRequest();

        when(directorService.findDirectorById("directorId")).thenThrow(DirectorNotFoundException.class);

        assertThrows(DirectorNotFoundException.class,
                () -> movieService.createMovie(movieRequest));

        verify(directorService).findDirectorById("directorId");
        verifyNoInteractions(publisherService);
        verifyNoInteractions(movieDtoConverter);
        verifyNoInteractions(movieRepository);
    }

    @Test
    void testUpdateMovie_whenUpdateMovieRequest_itShouldReturnMovieDto() {
        UpdateMovieRequest updateMovieRequest = generateUpdateMovieRequest();
        Movie updatedMovie = generateUpdatedMovie(generateMovie(), updateMovieRequest);
        MovieDto movieDto = generateMovieDto();

        when(movieRepository.findById("id")).thenReturn(Optional.ofNullable(generateMovie()));
        when(movieDtoConverter.convert(movieRepository.save(updatedMovie))).thenReturn(movieDto);

        MovieDto result = movieService.updateMovie("id", updateMovieRequest);

        assertEquals(movieDto, result);

        verify(movieRepository).findById("id");
        verify(movieDtoConverter).convert(movieRepository.save(updatedMovie));
    }

    @Test
    void testDeleteMovieById_whenExistId_shouldReturnStringAndDeletedId() {
        Movie movie = generateMovie();
        MovieDto movieDto = generateMovieDto();

        when(movieRepository.findById("id")).thenReturn(Optional.of(movie));
        when(movieDtoConverter.convert(movie)).thenReturn(movieDto);

        String result = movieService.deleteMovieById("id");

        assertEquals("movie deleted successfully " + "id", result);

        verify(movieRepository).findById("id");
        verify(movieDtoConverter).convert(movie);
    }

    @Test
    void testDeleteMovie_whenNotExistId_shouldThrowRuntimeException() {
        when(movieRepository.findById("id")).thenThrow(MovieNotFoundException.class);

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById("id"));

        verify(movieRepository).findById("id");
        verifyNoInteractions(movieDtoConverter);
    }
}
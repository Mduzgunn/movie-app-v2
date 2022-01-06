package com.md.movieappv2.service;

import com.md.movieappv2.TestSupport;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.*;
import com.md.movieappv2.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    private ReviewService reviewService;
    private UserService userService;
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        movieDtoConverter = mock(MovieDtoConverter.class);
        actorService = mock(ActorService.class);
        directorService = mock(DirectorService.class);
        publisherService = mock(PublisherService.class);
        reviewService = mock(ReviewService.class);
        userService = mock(UserService.class);

        movieService = new MovieService(actorService, directorService, publisherService,
                movieRepository, movieDtoConverter, reviewService, userService);
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
    void testGetAllMovieDtoList_shouldReturnListOfMovieDto() {

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

        assertEquals(movieDto,result);

        verify(directorService).findDirectorById("directorId");
        verify(publisherService).findPublisherById("publisherId");
        verify(actorService).findActorById("id");
        verify(movieDtoConverter).convert(movieRepository.save(movie));
    }

    @Test
    void testCreateMovie_whenPublisherIdNotExist_shouldThrowRuntimeException() {
        CreateMovieRequest movieRequest = generateCreateMovieRequest();

        when(publisherService.getPublisherById("publisherId")).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class,
                () -> movieService.createMovie(movieRequest));

        verify(publisherService).findPublisherById("publisherId");
//        Mockito.verifyNoInteractions(actorService);  create işlerim sırasında actor list publisherden önce kontrol edilir.
//        Mockito.verifyNoInteractions(directorService);
        verifyNoInteractions(movieRepository);
    }

    @Test
    void testCreateMovie_whenPublisherIdExistAndDirectorIdNotExist_shouldThrowRuntimeException() {

    }

    @Test
    void testUpdateMovie_whenUpdateMovieRequest_itShouldReturnMovieDto() {
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
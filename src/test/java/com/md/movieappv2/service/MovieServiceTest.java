package com.md.movieappv2.service;

import com.md.movieappv2.TestSupport;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        movieRepository = Mockito.mock(MovieRepository.class);
        movieDtoConverter = Mockito.mock(MovieDtoConverter.class);
        actorService = Mockito.mock(ActorService.class);
        directorService = Mockito.mock(DirectorService.class);
        publisherService = Mockito.mock(PublisherService.class);
        reviewService = Mockito.mock(ReviewService.class);
        userService=Mockito.mock(UserService.class);

        movieService = new MovieService(actorService, directorService, publisherService,
                movieRepository, movieDtoConverter, reviewService, userService);
    }

    @Test
    void testGetMovieById_whenIdExist_shouldReturnMovieDto() {
    }

    @Test
    void testGetMovieById_whenIdNotExist_shouldThrowNotFoundException() {
    }

    @Test
    void testGetAllMovieDtoList_shouldReturnListOfMovieDto() {
    }

    @Test
    void testCreateMovie_whenCreateMovieRequest_shouldReturnMovieDto() {
    }

    @Test
    void testCreateMovie_whenPublisherIdNotExist_shouldThrowRuntimeException(){
        CreateMovieRequest movieRequest = generateCreateMovieRequest();

        Mockito.when(publisherService.getPublisherById("publisherId")).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class,
                () -> movieService.createMovie(movieRequest));

        Mockito.verify(publisherService).getPublisherById("publisherId");
        Mockito.verifyNoInteractions(actorService);
        Mockito.verifyNoInteractions(directorService);
        Mockito.verifyNoInteractions(movieRepository);
    }

    @Test
    void testCreateMovie_whenPublisherIdExistAndDirectorIdNotExist_shouldThrowRuntimeException() {

    }

    @Test
    void testUpdateMovie_whenUpdateMovieRequest_itShouldReturnMovieDto() {
    }

    @Test
    void testDeleteMovieById_whenExistId_shouldReturnStringAndDeletedId() {
    }

    @Test
    void testDeleteMovie_whenNotExistId_shouldThrowRuntimeException() {

    }
}
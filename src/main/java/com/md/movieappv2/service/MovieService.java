package com.md.movieappv2.service;

import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.*;
import com.md.movieappv2.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDtoConverter movieDtoConverter;
    private final ActorService actorService;
    private final DirectorService directorService;
    private final PublisherService publisherService;

    public MovieService(ActorService actorService, DirectorService directorService, PublisherService publisherService,
                        MovieRepository movieRepository, MovieDtoConverter movieDtoConverter) {
        this.actorService = actorService;
        this.movieRepository = movieRepository;
        this.movieDtoConverter = movieDtoConverter;
        this.directorService = directorService;
        this.publisherService = publisherService;

    }

    protected Movie findMovieById(String id) {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException("movie not found " + id));
    }

    protected List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public MovieDto getMovieById(String id) {
        return movieDtoConverter.convert(findMovieById(id));
    }

    public List<MovieDto> getAllMovieDtoList() {
        return movieDtoConverter.convertToMovieDtoList(getAllMovies());
    }

    public MovieDto createMovie(CreateMovieRequest createMovieRequest) {
        List<Actor> actorList = new ArrayList<>(actorService.getActorList(createMovieRequest.getActors()));
        Director director = directorService.findDirectorById(createMovieRequest.getDirector());
        Publisher publisher = publisherService.findPublisherById(createMovieRequest.getPublisherId());

        Movie movie = new Movie(
                createMovieRequest.getName(),
                createMovieRequest.getReleaseYear(),
                createMovieRequest.getDescription(),
                createMovieRequest.getDuration(),
                createMovieRequest.getMedia(),
                createMovieRequest.getActive(),
                createMovieRequest.getGenre(),
                actorList,
                director,
                publisher
        );
        return movieDtoConverter.convert(movieRepository.save(movie));
    }

    public MovieDto updateMovie(String id, UpdateMovieRequest updateMovieRequest) {
        Movie movie = findMovieById(id);

        Movie updatedMovie = new Movie(
                movie.getId(),
                movie.getName(),
                movie.getReleaseYear(),
                movie.getDescription(),
                movie.getDuration(),
                updateMovieRequest.getMedia(),
                updateMovieRequest.isActive(),
                movie.getGenre(),
                movie.getCreationDate(),
                movie.getUpdatedDate(),
                movie.getActors(),
                movie.getDirector(),
                movie.getPublisher(),
                movie.getReviews()

        );
        return movieDtoConverter.convert(movieRepository.save(updatedMovie));
    }

    public String deleteMovieById(String id) {
        getMovieById(id);
        movieRepository.deleteById(id);
        return "movie deleted successfully " + id;
    }

}

package com.md.movieappv2.service;

import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.MovieDtoConverter;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.model.Movie;
import com.md.movieappv2.model.Publisher;
import com.md.movieappv2.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDtoConverter movieDtoConverter;
    private final ActorService actorService;
    private final DirectorService directorService;
    private final PublisherService publisherService;
    private final ReviewService reviewService;
    private final UserService userService;

    public MovieService(ActorService actorService, DirectorService directorService,PublisherService publisherService,
                        MovieRepository movieRepository, MovieDtoConverter movieDtoConverter,
                        ReviewService reviewService, UserService userService) {
        this.actorService = actorService;
        this.movieRepository = movieRepository;
        this.movieDtoConverter = movieDtoConverter;
        this.directorService = directorService;
        this.publisherService = publisherService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    protected Movie findMovieById(String id) {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new MovieNotFoundException("post not found " + id));
    }

    protected List<Movie> getAllMovies() {
        return movieRepository  .findAll();
    }

    public MovieDto getMovieById(String id) {
        return movieDtoConverter.convert(findMovieById(id));
    }

    public List<MovieDto> getAllMovieDtoList() {
        return movieDtoConverter.convertToMovieDtoList(getAllMovies());
    }

    public MovieDto createMovie(CreateMovieRequest createMovieRequest) {
///genre language
        List<Actor> actorList = new ArrayList<>(actorService.getActorList(createMovieRequest.getActors()));
        Director director = directorService.findDirectorById(createMovieRequest.getDirector());
        Publisher publisher = publisherService.findPublisherById(createMovieRequest.getPublisher());
        Movie movie = new Movie(
                createMovieRequest.getName(),
createMovieRequest.getReleaseYear(),
createMovieRequest.getDescription(),
createMovieRequest.getDuration(),
createMovieRequest.getMedia(),
createMovieRequest.isActive(),
createMovieRequest.getGenre(),
actorList,
director,
publisher,
review

//                createMovieRequest.getName(),
//                createMovieRequest.getReleaseYear(),
//                createMovieRequest.getDescription(),
//                createMovieRequest.getDuration(),
//                createMovieRequest.getMedia(),
//                createMovieRequest.isActive(),
//                createMovieRequest.getGenre(),
//                actorList,
//                director,
//                publisher,
//                createMovieRequest.getLanguage()

                );
        return movieDtoConverter.convert(movieRepository.save(movie));
    }

    public MovieDto updateMovie (String id, UpdateMovieRequest updateMovieRequest) {
        Movie movie = findMovieById(id);

        Movie updatedMovie = new Movie(
                movie.getId(),
                movie.getName(),
                movie.getReleaseYear(),
                movie.getDescription(),
                movie.getDuration(),
                updateMovieRequest.getMedia(),
                updateMovieRequest.isActive(),
                movie.getCreationDate(),
                movie.getUpdatedDate(),
                movie.getGenre(),
                movie.getActors(),
                movie.getDirector(),
                updateMovieRequest.getPublisher(),
                movie.getReviews(),
                movie.getLanguage()

                );
        return movieDtoConverter.convert(movieRepository.save(updatedMovie));
    }

}

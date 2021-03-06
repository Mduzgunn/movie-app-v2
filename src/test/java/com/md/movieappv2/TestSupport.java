package com.md.movieappv2;

import com.md.movieappv2.dto.*;
import com.md.movieappv2.dto.request.CreateActorRequest;
import com.md.movieappv2.dto.request.CreateDirectorRequest;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.model.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class TestSupport {

    public Instant getCurrentInstant() {
        String instantExpected = "2022-06-15T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());
        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }


    public CreateMovieRequest generateCreateMovieRequest() {
        return new CreateMovieRequest(
                "name",
                2021,
                "description",
                100,
                "media",
                true,
                List.of(Genre.COMEDY),
                List.of("actorId1"),
                "directorId",
                "publisherId"
        );
    }

    public Movie generateMovie() {
        Director director = generateDirector();
        Publisher publisher = generatePublisher();
        return new Movie(
                "name",
                2021,
                "description",
                111,
                "media",
                true,
                List.of(Genre.DRAMA),
                List.of(generateActor()),
                director,
                publisher
        );
    }

    public MovieDto generateMovieDto() {
        ActorDto actorDto = generateActorDto();
        return new MovieDto(
                "id",
                "name",
                2021,
                "description",
                111,
                "media",
                true,
                List.of(Genre.HORROR),
                getLocalDateTime(),
                getLocalDateTime(),
                List.of(actorDto),
                generateDirectorDto(),
                generatePublisher().getName(),
                generateReviewDtoList()
        );
    }

    public List<Movie> generateMovieList() {
        Movie movie = generateMovie();
        return List.of(movie);
    }

    public List<MovieDto> generateMovieDtoList() {
        return List.of(generateMovieDto());
    }


    public UpdateMovieRequest generateUpdateMovieRequest() {
        return new UpdateMovieRequest(
                "media2",
                false,
                List.of("")
        );
    }

    public Movie generateUpdatedMovie(Movie from, UpdateMovieRequest updateMovieRequest) {
        return new Movie(
                from.getId(),
                from.getName(),
                from.getReleaseYear(),
                from.getDescription(),
                from.getDuration(),
                updateMovieRequest.getMedia(),
                updateMovieRequest.isActive(),
                from.getGenre(),
                getLocalDateTime(),
                getLocalDateTime(),
                from.getActors(),
                from.getDirector(),
                from.getPublisher(),
                from.getReviews()
        );
    }


    /*** publisher */

    public Publisher generatePublisher() {
        return new Publisher(
                "publisherId",
                "publisherName"
        );
    }

    public PublisherDto generatePublisherDto() {
        return new PublisherDto(
                "publisherId",
                "publisher name"
        );
    }

//   *** director

    public Director generateDirector() {
        return new Director(
                "directorId",
                "director name",
                "director lastname"
        );
    }

    public DirectorDto generateDirectorDto() {
        return new DirectorDto(
                "directorId",
                "director name",
                "director lastname"
        );
    }

    public List<Director> generateDirectorList() {
        Director director = generateDirector();
        return List.of(director);
    }

    public List<DirectorDto> generateDirectorDtoList() {
        return List.of(generateDirectorDto());
    }

    public CreateDirectorRequest generateCreateDirectorRequest() {
        return new CreateDirectorRequest(
           "director name",
           "director lastname"
        );
    }



    //***review

    public Review generateReview() {
        User user = generateUser();
        Movie movie = generateMovie();
        return new Review(
                "id",
                "comment",
                4,
                getLocalDateTime(),
                getLocalDateTime(),
                user,
                movie
        );
    }

    public List<Review> generateReviewList() {
        return List.of(generateReview());
    }

    public ReviewDto generateReviewDto() {
        UserDto userDto = generateUserDto();
        return new ReviewDto(
                "id",
                "comment",
                3,
                getLocalDateTime(),
                getLocalDateTime(),
                userDto
        );
    }

    public List<ReviewDto> generateReviewDtoList() {
        return List.of(generateReviewDto());
    }

    //*** user

    public User generateUser() {
        return new User(

                "username",
                "email"
        );
    }

    public List<User> generateUserList() {
        return List.of(generateUser());
    }

    public UserDto generateUserDto() {
        return new UserDto(
                "id",
                "username",
                "user mail"
        );
    }

    public List<UserDto> generateUserDtoList() {
        return List.of(generateUserDto());
    }

    // ***actor


    public CreateActorRequest generateCreateActorRequest() {
        return new CreateActorRequest(
            "firstname",
            "lastname"
        );
    }

    public Actor generateActor() {
        return new Actor(
                "id",
                "name",
                "lastname"

        );
    }

    public List<Actor> generateActorList() {
        return List.of(generateActor());
    }

    public ActorDto generateActorDto() {
        return new ActorDto(
                "id",
                "actor firstname",
                "actor lastname"

        );
    }

    public List<ActorDto> generateActorDtoList() {
        return List.of(generateActorDto());
    }

}

package com.md.movieappv2;

import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.model.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TestSupport {

    public Instant getCurrentInstant() {
        String instantExpected = "2021-06-15T10:15:30Z";
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
                List.of("actorId1", "actorId2"),
                "directorId",
                "publisherId",
                List.of(Language.EN));

    }

    public Movie generateMovie() {
        List<Actor> actor = generateActorList();
//        Director director = generateDirector();
//        Publisher publisher = generatePublisher();
        List<Review> review = generateReviewList();
        return new Movie(
                "name",
                2021,
                "description",
                111,
                "media",
                true,
                List.of(Genre.DRAMA),
                actor,
                generateDirector(),
                generatePublisher(),
                review,
                List.of(Language.EN)

        );
    }

//    public MovieDto generateMovieDto() {
//        return new MovieDto(
//
//                Director director = generateDirector();
//                Publisher publisher = generatePublisher();
//                List<Review> review = generateReviewList();
//                return new Movie(
//                    "name",
//                    2021,
//                    "description",
//                    111,
//                    "media",
//                    true,
//                    List.of(Genre.DRAMA),
////                    actor,
//                    director,
//                    publisher,
//                    review,
//                    List.of(Language.EN)
//
//        );
//    }

    public List<Movie> generateListMovie() {
        Movie user = generateMovie();
        return List.of(user);
    }

//    public List<MovieDto> generateListsMovieDto() {
//        MovieDto movieDto = generateMovieDto();
//        return List.of(movieDto);
//    }



    public UpdateMovieRequest generateUpdateMovieRequest() {
        Publisher publisher = generatePublisher();

        return new UpdateMovieRequest(
                "username",
                true,
                publisher
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
                updateMovieRequest.getPublisher(),
                from.getReviews(),
                from.getLanguage()
        );
    }


    /*** publisher */

    public Publisher generatePublisher() {
        return new Publisher(
                "pub name"
        );
    }

//   *** director

    public Director generateDirector() {
        return new Director(
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
                "yorum",
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

    //*** user

    public User generateUser() {
        return new User(

                "username",
                "email"
        );
    }


    // ***actor

    public Actor generateActor() {
        return new Actor(
                "name",
                "lastname"

        );
    }

    public List<Actor> generateActorList() {
        return List.of(generateActor());
    }

    public ActorDto generateActorDto() {
        ActorDto actorDto = generateActorDto();
        return new ActorDto(
                "id",
                "actor firstname",
                "actor lastname",
                Collections.emptyList()
        );
    }

    public List<ActorDto> generateActorDtoList() {
        return List.of(generateActorDto());
    }

}

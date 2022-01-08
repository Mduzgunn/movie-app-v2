package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.*;
import com.md.movieappv2.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MovieDtoConverter {
    public MovieDto convert(Movie from) {
        return new MovieDto(
                from.getId(),
                from.getName(),
                from.getReleaseYear(),
                from.getDescription(),
                from.getDuration(),
                from.getMedia(),
                from.isActive(),
                Objects.requireNonNull(from.getGenre()),
                from.getCreationDate(),
                from.getUpdatedDate(),
                getActorList(new ArrayList<>(from.getActors())),
                new DirectorDto(from.getDirector().getId(),
                        from.getDirector().getName(),
                        from.getDirector().getLastName()),

                // TODO - id?
                from.getPublisher().getName(),
                getReviewList(new ArrayList<>(from.getReviews()))
//                from.getLanguage()
                );
    }
    private List<ActorDto> getActorList(List<Actor> actorList) {
        return actorList.stream()
                .map(a -> new ActorDto(
                        a.getId(),
                        a.getFirstName(),
                        a.getLastName()))
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewList(List<Review> reviewList) {
        return reviewList.stream()
                .map(r -> new ReviewDto(
                        r.getId(),
                        r.getComment(),
                        r.getStars()
                ))
                .collect(Collectors.toList());
    }

    public List<MovieDto> convertToMovieDtoList(List<Movie> movieList) {
        return movieList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }



}

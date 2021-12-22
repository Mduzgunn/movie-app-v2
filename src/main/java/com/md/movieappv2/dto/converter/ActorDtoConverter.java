package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.UserDto;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.model.Movie;
import com.md.movieappv2.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ActorDtoConverter {

    public ActorDto convert(Actor from) {
        return new ActorDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                getMovieList(new ArrayList<>(Objects.requireNonNull(from.getMovies())))
        );
    }

    private List<MovieDto> getMovieList(List<Movie> movies) {
        return movies.stream()
                .map(m -> new MovieDto(
                        m.getId(),
                        m.getName(),
                        m.getReleaseYear(),
                        m.getDescription(),
                        // getMovieDurationString(m.getDuration()),
                        m.getDuration(),
                        m.getMedia(),
                        m.getGenre(),
                        m.getCreationDate(),
                        m.getUpdatedDate(),
                        m.isActive()))
                .collect(Collectors.toList());
    }

    public List<ActorDto> convertToActorDtoList(List<Actor> actorList) {
        return actorList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

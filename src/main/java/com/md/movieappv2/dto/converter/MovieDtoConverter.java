package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.*;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.model.Movie;
import com.md.movieappv2.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
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
                from.getGenre(),
                from.getCreationDate(),
                from.getUpdatedDate(),
                from.isActive(),
                getActorList(from.getActors().stream().collect(Collectors.toList())),
                new DirectorDto(from.getDirector().getId(),
                        from.getDirector().getName(),
                        from.getDirector().getLastName()),
                new PublisherDto(from.getId(),
                        from.getName())

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

    public List<MovieDto> convertToMovieDtoList(List<Movie> movieList) {
        return movieList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

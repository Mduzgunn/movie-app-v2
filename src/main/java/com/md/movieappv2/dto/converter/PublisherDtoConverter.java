package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.PublisherDto;
import com.md.movieappv2.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherDtoConverter {
    public PublisherDto convert(Publisher from) {
        return new PublisherDto(
                from.getId(),
                from.getName()
//                new MovieDto(from.getMovie().getId(),
//                        from.getMovie().getName(),
//                        from.getMovie().getReleaseYear(),
//                        from.getMovie().getDescription(),
//                        from.getMovie().getDuration(),
//                        from.getMovie().getMedia(),
//                        from.getMovie().isActive(),
//                        from.getMovie().getGenre()
////                        from.getMovie().getDirector()
//                )
        );
    }
    public List<PublisherDto> convertToPublisherDtoList(List<Publisher> publisherList) {
        return publisherList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

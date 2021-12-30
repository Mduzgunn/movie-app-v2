package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.model.Director;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectorDtoConverter {
    public DirectorDto convert(Director from) {
        return new DirectorDto(
                from.getId(),
                from.getName(),
                from.getLastName()
        );
    }
    public List<DirectorDto> convertToDirectorDtoList(List<Director> directorList) {
        return directorList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

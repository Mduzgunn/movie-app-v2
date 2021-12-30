package com.md.movieappv2.service;

import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.ActorDtoConverter;
import com.md.movieappv2.exception.ActorNotFoundException;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.model.Movie;
import com.md.movieappv2.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorService {
    private final ActorRepository  actorRepository;
    private final ActorDtoConverter actorDtoConverter;

    public ActorService(ActorRepository actorRepository, ActorDtoConverter actorDtoConverter) {
        this.actorRepository = actorRepository;
        this.actorDtoConverter = actorDtoConverter;
    }

    protected Actor findActorById(String id) {
        return actorRepository
                .findById(id)
                .orElseThrow(() -> new ActorNotFoundException("actor not found " + id));
    }

    protected List<Actor> getActorList(List<String> idList) {
        return Optional.of(actorRepository.findAllByIdIn(idList))
                .filter(a -> !a.isEmpty())
                .orElse(List.of(
                        new Actor("id",
                                "firstname",
                                "",
                                null)
                ));
    }

    public ActorDto getActorById(String id) {
        return actorDtoConverter.convert(findActorById(id));
    }

//    protected List<Actor> getAllActors() {
//        return actorRepository.findAll();
//    }

    public List<ActorDto> getAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(actorDtoConverter::convert)
                .collect(Collectors.toList());
    }





//    public List<ActorDto> getAllActorDtoList() {
//        return actorDtoConverter.convertToActorDtoList(getAllActors());
//    }
}

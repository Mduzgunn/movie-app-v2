package com.md.movieappv2.service;

import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.converter.ActorDtoConverter;
import com.md.movieappv2.dto.request.CreateActorRequest;
import com.md.movieappv2.dto.request.UpdateActorRequest;
import com.md.movieappv2.exception.ActorNotFoundException;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    protected List<Actor> getActorListByIdList(List<String> idList) {
        return Optional.of(actorRepository.findAllByIdIn(idList))
                .filter(a -> !a.isEmpty())
                .orElse(List.of(
                        new Actor("id",
                                "firstname",
                                "lastname")
                ));
    }

    public ActorDto getActorById(String id) {
        return actorDtoConverter.convert(findActorById(id));
    }

    protected List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public ActorDto createActor(CreateActorRequest createActorRequest) {
        Actor actor = new Actor(
                createActorRequest.getFirstName(),
                createActorRequest.getLastName()
        );
        return actorDtoConverter.convert(actorRepository.save(actor));
    }

    public ActorDto updateActor(String id, UpdateActorRequest updateActorRequest) {
        Actor actor = findActorById(id);

        Actor updatedActor = new Actor(
                actor.getId(),
                updateActorRequest.getFirstName(),
                updateActorRequest.getLastName()
        );
        return actorDtoConverter.convert(actorRepository.save(updatedActor));
    }


    public String deleteActorById(String id) {
        getActorById(id);
        actorRepository.deleteById(id);
        return "actor deleted successfully "+id;
    }


    public List<ActorDto> getAllActorDtoList() {
        return actorDtoConverter.convertToActorDtoList(getAllActors());
    }
}

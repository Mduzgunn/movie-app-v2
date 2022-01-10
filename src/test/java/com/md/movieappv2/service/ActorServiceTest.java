package com.md.movieappv2.service;

import com.md.movieappv2.TestSupport;
import com.md.movieappv2.dto.ActorDto;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.converter.ActorDtoConverter;
import com.md.movieappv2.dto.request.CreateActorRequest;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.exception.ActorNotFoundException;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.exception.MovieNotFoundException;
import com.md.movieappv2.model.Actor;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.model.Movie;
import com.md.movieappv2.model.Publisher;
import com.md.movieappv2.repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ActorServiceTest extends TestSupport {
    private ActorRepository actorRepository;
    private ActorDtoConverter actorDtoConverter;
    private ActorService actorService;

    @BeforeEach
    void setUp() {
        actorRepository = mock(ActorRepository.class);
        actorDtoConverter = mock(ActorDtoConverter.class);
        actorService = new ActorService(actorRepository, actorDtoConverter);
    }

    @Test
    void testGetActorById_whenIdExist_shouldReturnActorDto() {
        Actor actor = generateActor();
        ActorDto actorDto = generateActorDto();

        when(actorRepository.findById("id")).thenReturn(Optional.of(actor));
        when(actorDtoConverter.convert(actor)).thenReturn(actorDto);

        ActorDto result = actorService.getActorById("id");

        assertEquals(actorDto, result);

        verify(actorRepository).findById("id");
        verify(actorDtoConverter).convert(actor);
    }

    @Test
    void testGetActorById_whenIdNotExist_shouldThrowNotFoundException() {
        when(actorRepository.findById("id")).thenThrow(ActorNotFoundException.class);

        assertThrows(ActorNotFoundException.class, () -> actorService.getActorById("id"));

        verify(actorRepository).findById("id");
        verifyNoInteractions(actorDtoConverter);
    }

    @Test
    void testGetActorDtoList_shouldReturnListOfActorDto() {
        List<Actor> actorList = generateActorList();
        List<ActorDto> actorDtoList = generateActorDtoList();

        when(actorRepository.findAll()).thenReturn(actorList);
        when(actorDtoConverter.convertToActorDtoList(actorList)).thenReturn(actorDtoList);

        List<ActorDto> result = actorService.getAllActorDtoList();

        assertEquals(actorDtoList, result);

        verify(actorRepository).findAll();
        verify(actorDtoConverter).convertToActorDtoList(actorList);
    }

    @Test
    void testCreateActor_whenCreateActorRequest_shouldReturnActorDto() {
        CreateActorRequest createActorRequest = generateCreateActorRequest();
        ActorDto actorDto = generateActorDto();
        Actor actor = generateActor();

        when(actorDtoConverter.convert(actorRepository.save(actor))).thenReturn(actorDto);

        ActorDto result = actorService.createActor(createActorRequest);
        assertEquals(actorDto, result);

        verify(actorDtoConverter).convert(actorRepository.save(actor));
    }

    @Test
    void testDeleteActorById_whenExistId_shouldReturnStringAndDeletedId() {
        ActorDto actorDto = generateActorDto();
        Actor actor = generateActor();

        when(actorRepository.findById("id")).thenReturn(Optional.of(actor));
        when(actorDtoConverter.convert(actor)).thenReturn(actorDto);

        String result = actorService.deleteActorById("id");

        assertEquals("actor deleted successfully " + "id", result);

        verify(actorRepository).findById("id");
        verify(actorDtoConverter).convert(actor);
    }

    @Test
    void testDeleteActor_whenNotExistId_shouldThrowRuntimeException() {
        when(actorRepository.findById("id")).thenThrow(ActorNotFoundException.class);

        assertThrows(ActorNotFoundException.class, () -> actorService.getActorById("id"));

        verify(actorRepository).findById("id");
        verifyNoInteractions(actorDtoConverter);
    }

}

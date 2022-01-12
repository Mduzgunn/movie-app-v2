package com.md.movieappv2.service;

import com.md.movieappv2.TestSupport;
import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.dto.converter.DirectorDtoConverter;
import com.md.movieappv2.dto.request.CreateDirectorRequest;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.repository.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DirectorServiceTest extends TestSupport {

    private DirectorRepository directorRepository;
    private DirectorDtoConverter directorDtoConverter;
    private DirectorService directorService;


    @BeforeEach
    void setUp() {
        directorRepository = mock(DirectorRepository.class);
        directorDtoConverter = mock(DirectorDtoConverter.class);
        directorService = new DirectorService(directorRepository, directorDtoConverter);
    }


    @Test
    void testGetDirectorById_whenIdExist_shouldReturnDirectorDto() {
        Director director = generateDirector();
        DirectorDto directorDto = generateDirectorDto();

        when(directorRepository.findById("id")).thenReturn(Optional.of(director));
        when(directorDtoConverter.convert(director)).thenReturn(directorDto);

        DirectorDto result = directorService.getDirectorById("id");

        assertEquals(directorDto, result);

        verify(directorRepository).findById("id");
        verify(directorDtoConverter).convert(director);
    }

    @Test
    void testGetDirectorById_whenIdNotExist_shouldThrowNotFoundException() {
        when(directorRepository.findById("id")).thenThrow(DirectorNotFoundException.class);

        assertThrows(DirectorNotFoundException.class, () -> directorService.getDirectorById("id"));

        verify(directorRepository).findById("id");
        verifyNoInteractions(directorDtoConverter);
    }

    @Test
    void testGetDirectorDtoList_shouldReturnListOfDirectorDto() {
        List<Director> directorList = generateDirectorList();
        List<DirectorDto> directorDtoList = generateDirectorDtoList();

        when(directorRepository.findAll()).thenReturn(directorList);
        when(directorDtoConverter.convertToDirectorDtoList(directorList)).thenReturn(directorDtoList);

        List<DirectorDto> result = directorService.getAllDirectorDtoList();

        assertEquals(directorDtoList, result);

        verify(directorRepository).findAll();
        verify(directorDtoConverter).convertToDirectorDtoList(directorList);
    }

    @Test
    void testCreateDirector_whenCreateDirectorRequest_shouldReturnDirectorDto() {
        CreateDirectorRequest createDirectorRequest = generateCreateDirectorRequest();
        Director director = generateDirector();
        DirectorDto directorDto = generateDirectorDto();

        when(directorDtoConverter.convert(directorRepository.save(director))).thenReturn(directorDto);

        DirectorDto result = directorService.createDirector(createDirectorRequest);

        assertEquals(directorDto, result);

        verify(directorDtoConverter).convert(directorRepository.save(director));
    }

    @Test
    void testDeleteDirectorById_whenExistId_shouldReturnStringAndDeletedId() {
        Director director = generateDirector();
        DirectorDto directorDto = generateDirectorDto();

        when(directorRepository.findById("id")).thenReturn(Optional.of(director));
        when(directorDtoConverter.convert(director)).thenReturn(directorDto);

        String result = directorService.deleteDirectorById("id");

        assertEquals("director deleted successfully " + "id", result);

        verify(directorRepository).findById("id");
        verify(directorDtoConverter).convert(director);
    }

    @Test
    void testDeleteDirector_whenNotExistId_shouldThrowRuntimeException() {
        when(directorRepository.findById("id")).thenThrow(DirectorNotFoundException.class);

        assertThrows(DirectorNotFoundException.class, () -> directorService.getDirectorById("id"));

        verify(directorRepository).findById("id");
        verifyNoInteractions(directorDtoConverter);
    }
}

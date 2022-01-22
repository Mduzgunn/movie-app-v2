package com.md.movieappv2.service;

import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.dto.converter.DirectorDtoConverter;
import com.md.movieappv2.dto.request.CreateDirectorRequest;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorDtoConverter directorDtoConverter;

    public DirectorService(DirectorRepository directorRepository, DirectorDtoConverter directorDtoConverter) {
        this.directorRepository = directorRepository;
        this.directorDtoConverter = directorDtoConverter;
    }

    protected Director findDirectorById(String id) {
        return directorRepository
                .findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("director not found " + id));
    }

    protected List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public DirectorDto createDirector(CreateDirectorRequest createDirectorRequest) {
        Director director = new Director(
                createDirectorRequest.getName(),
                createDirectorRequest.getLastName()
        );
        return directorDtoConverter.convert(directorRepository.save(director));
    }

    public String deleteDirectorById(String id) {
        getDirectorById(id);
        directorRepository.deleteById(id);
        return "director deleted successfully "+id;
    }

    public DirectorDto getDirectorById(String id) {
        return directorDtoConverter.convert(findDirectorById(id));
    }

    public List<DirectorDto> getAllDirectorDtoList() {
        return directorDtoConverter.convertToDirectorDtoList(getAllDirectors());
    }
}

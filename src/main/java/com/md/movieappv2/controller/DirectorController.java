package com.md.movieappv2.controller;

import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.request.CreateDirectorRequest;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.service.DirectorService;
import com.md.movieappv2.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/v2/director")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<DirectorDto> createDirector(@Valid @RequestBody CreateDirectorRequest createDirectorRequest) {
        return ResponseEntity.ok(directorService.createDirector(createDirectorRequest));
    }

    @GetMapping
    public ResponseEntity<List<DirectorDto>> getDirectors() {
        List<DirectorDto> directorDtoList = directorService.getAllDirectorDtoList();
        return ResponseEntity.ok(directorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getActorById(@PathVariable String id) {
        DirectorDto directorDto = directorService.getDirectorById(id);
        return ResponseEntity.ok(directorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirectorById(@PathVariable String id) {
        return ResponseEntity.ok(directorService.deleteDirectorById(id));
    }
}

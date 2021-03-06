package com.md.movieappv2.controller;

import com.md.movieappv2.dto.MovieDto;
import com.md.movieappv2.dto.request.CreateMovieRequest;
import com.md.movieappv2.dto.request.UpdateMovieRequest;
import com.md.movieappv2.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v2/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        return ResponseEntity.ok(movieService.createMovie(createMovieRequest));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getMovies() {
        List<MovieDto> movieDtoList = movieService.getAllMovieDtoList();
        return ResponseEntity.ok(movieDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) {
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable String id,@Valid @RequestBody UpdateMovieRequest updateMovieRequest) {
        return ResponseEntity.ok(movieService.updateMovie(id, updateMovieRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.deleteMovieById(id));
    }
}

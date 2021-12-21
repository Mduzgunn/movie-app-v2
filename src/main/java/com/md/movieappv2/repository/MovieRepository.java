package com.md.movieappv2.repository;

import com.md.movieappv2.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,String> {
}

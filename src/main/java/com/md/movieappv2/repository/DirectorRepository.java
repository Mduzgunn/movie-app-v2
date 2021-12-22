package com.md.movieappv2.repository;

import com.md.movieappv2.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director,String> {
}

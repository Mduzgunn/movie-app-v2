package com.md.movieappv2.repository;

import com.md.movieappv2.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,String> {
}

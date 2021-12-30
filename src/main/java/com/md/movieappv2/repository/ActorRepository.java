package com.md.movieappv2.repository;

import com.md.movieappv2.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor,String> {
    List<Actor> findAllByIdIn(List<String> idList);

}

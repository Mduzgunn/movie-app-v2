package com.md.movieappv2.repository;

import com.md.movieappv2.model.RateKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<RateKey,Integer> {
}

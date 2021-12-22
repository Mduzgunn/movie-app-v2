package com.md.movieappv2.repository;

import com.md.movieappv2.model.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<ReviewKey,Integer> {
}

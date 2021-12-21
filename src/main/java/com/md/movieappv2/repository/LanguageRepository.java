package com.md.movieappv2.repository;

import com.md.movieappv2.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,String> {
}

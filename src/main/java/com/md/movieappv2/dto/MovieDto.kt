package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.md.movieappv2.model.Genre
import java.time.LocalDateTime

data class MovieDto @JvmOverloads constructor(
        val id: String,
        val name: String,
        val releaseYear: Int,
        val description: String,
        val duration: Int,
        val media: String,
        val genre: List<Genre>,
        val creationDate: LocalDateTime = LocalDateTime.now(),
        val updatedDate: LocalDateTime = LocalDateTime.now(),
        val isActive: Boolean,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val actors: List<ActorDto>? = ArrayList(),
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val director: DirectorDto?=null,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val publisher: PublisherDto?=null
)

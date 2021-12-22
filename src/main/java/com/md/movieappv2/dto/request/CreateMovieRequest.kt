package com.md.movieappv2.dto.request

import com.md.movieappv2.model.Genre
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreateMovieRequest (
        @field:NotBlank
        val name: String,
        @field:NotBlank
        @field:Min(2000)
        val releaseYear: Int,
        @field:NotBlank
        val description: String,
        @field:NotBlank
        val duration: Int,
        val media: String,
        val genre: List<Genre>? = ArrayList(),
        val isActive: Boolean,
        @field:NotBlank
        val actors: List<String>?= ArrayList(),
        @field:NotBlank
        val director: String,
        @field:NotBlank
        val publisher: String
        )

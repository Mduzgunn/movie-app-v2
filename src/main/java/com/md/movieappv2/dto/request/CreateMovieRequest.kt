package com.md.movieappv2.dto.request

import com.md.movieappv2.model.Genre
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateMovieRequest (
        @field:NotBlank
        val name: String,
        @field:NotNull
        @field:Min(2000)
        val releaseYear: Int,
        @field:NotBlank
        val description: String,
        val duration: Int,
        val media: String,
        val active: Boolean,
        @field:Enumerated(EnumType.STRING)
        val genre: List<Genre>?= ArrayList(),
        val actorIds: List<String>? = ArrayList(),
        @field:NotNull
        val director: String?,
        val publisherId: String?,

        )

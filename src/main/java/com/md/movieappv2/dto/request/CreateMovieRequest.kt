package com.md.movieappv2.dto.request

import com.md.movieappv2.model.Genre
import com.md.movieappv2.model.Language
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreateMovieRequest (
        @field:NotBlank
        val name: String?,
        @field:NotBlank
        @field:Min(2000)
        val releaseYear: Int?,
        @field:NotBlank
        val description: String?,
        @field:NotBlank
        val duration: Int?,
        val media: String?,
        val isActive: Boolean?,
//        @field:Enumerated(EnumType.STRING)
//        val genre: Genre?,
        val genre: List<Genre>?,
//        val actor: String?,
//        @field:NotBlank
        val actors: List<String>?,
        @field:NotBlank
        val director: String?,
        @field:NotBlank
        val publisher: String?,

        val language: List<Language>?
        ///LANGUAge ??
        )

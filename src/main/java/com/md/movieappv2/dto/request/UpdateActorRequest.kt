package com.md.movieappv2.dto.request

import com.md.movieappv2.dto.MovieDto
import com.md.movieappv2.model.Movie

data class UpdateActorRequest(
    val firstName: String?,
    val lastName: String?,
    val movieList: List<Movie>?
)

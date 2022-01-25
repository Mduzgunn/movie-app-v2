package com.md.movieappv2.dto.request

import com.md.movieappv2.model.Movie
import com.sun.istack.Nullable

data class UpdateActorRequest(
    @Nullable
    val firstName: String?,
    @Nullable
    val lastName: String?,
    @Nullable
    val movieIds: List<String>?
)

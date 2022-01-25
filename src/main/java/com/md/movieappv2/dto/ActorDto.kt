package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class ActorDto @JvmOverloads constructor(
        val id: String?,
        val firstName: String,
        val lastName:String,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val movie: List<MovieDto>? = ArrayList()
)

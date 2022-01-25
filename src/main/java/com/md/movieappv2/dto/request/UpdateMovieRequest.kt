package com.md.movieappv2.dto.request

data class UpdateMovieRequest(

        val media: String,
        val isActive: Boolean,
        val actorIds: List<String>? = ArrayList()
)

package com.md.movieappv2.dto.request

import com.md.movieappv2.model.Publisher

data class UpdateMovieRequest(

        val media: String,
        val isActive: Boolean,
        val publisher: Publisher?  //Sttringggg
)

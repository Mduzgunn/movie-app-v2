package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class PublisherDto @JvmOverloads constructor(
        val id: String?,
        val name: String,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val movie: MovieDto?=null,

)

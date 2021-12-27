package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class ReviewDto @JvmOverloads constructor(
        val id: String?,
        val movieId: Int,
        val rate: Double,
        val creationDate: LocalDateTime,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val user: UserDto?=null,

        )

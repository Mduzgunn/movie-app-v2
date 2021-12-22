package com.md.movieappv2.dto

import java.time.LocalDateTime

data class ReviewDto @JvmOverloads constructor(
        val id: String?,
        val movieId: Int,
        val rate: Double,
        val creationDate: LocalDateTime,

        val user: UserDto,

        )

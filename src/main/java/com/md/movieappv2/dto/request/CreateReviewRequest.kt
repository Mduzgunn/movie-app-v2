package com.md.movieappv2.dto.request

import com.md.movieappv2.dto.UserDto
import javax.validation.constraints.Min

data class CreateReviewRequest(
        val movieId: Int,
        @field:Min(1)
        val rate: Double,
        val user: UserDto,
)

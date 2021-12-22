package com.md.movieappv2.dto.request

import javax.validation.constraints.NotBlank

data class CreateUserRequest (
        @field:NotBlank
        val username: String,
        @field:NotBlank
        val email: String,
        )
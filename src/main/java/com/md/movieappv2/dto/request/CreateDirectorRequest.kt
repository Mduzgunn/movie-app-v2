package com.md.movieappv2.dto.request

import javax.validation.constraints.NotBlank

data class CreateDirectorRequest(
        @field:NotBlank
        val name: String,
        val lastName: String
)
package com.md.movieappv2.dto.request

import javax.validation.constraints.NotBlank

data class CreateActorRequest(

        @field:NotBlank
        val firstName: String,
        val lastName: String
)


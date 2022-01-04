package com.md.movieappv2.dto.request

import javax.validation.constraints.NotBlank

data class CreatePublisherRequest(
        @field:NotBlank
        val name: String
)

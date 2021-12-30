package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class ReviewDto @JvmOverloads constructor(
        val id: String?,
        val comment: String?,
        val Stars: Int?,
        val creationDate: LocalDateTime= LocalDateTime.now(),
        val updatedDate: LocalDateTime= LocalDateTime.now(),

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val user: UserDto?=null,

        )

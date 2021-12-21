package com.md.movieappv2.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class UserDto @JvmOverloads constructor(
        val id: String?,
        val username: String,
        val email: String,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val creationDate: LocalDateTime?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val updatedDate: LocalDateTime?=null,
)

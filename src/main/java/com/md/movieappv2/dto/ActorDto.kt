package com.md.movieappv2.dto

data class ActorDto @JvmOverloads constructor(
        val id: String?,
        val firstName: String,
        val lastName:String,
        val role:String
)

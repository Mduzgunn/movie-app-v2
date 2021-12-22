package com.md.movieappv2.model

//import kotlinx.serialization.Serializable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ReviewKey(
        @Column(name = "movie_id", insertable = false, updatable = false)
        val movieId: String,
        @Column(name = "user_id", insertable = false, updatable = false)
        val userId: String,
) : Serializable {}


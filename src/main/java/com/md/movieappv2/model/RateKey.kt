package com.md.movieappv2.model

//import kotlinx.serialization.Serializable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
//@Serializable
data class RateKey (

//@Embeddable
//data class UserReviewId(
//@Column(name = "user_id", insertable = false, updatable = false)
//val userId: String,
//@Column(name = "review_id", insertable = false, updatable = false)
//val reviewId: String
//): Serializable{}

        @Column(name = "movie_id")
        val movieId: String,
        @Column(name = "user_id")
        val userId: String,


):Serializable{}


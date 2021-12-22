package com.md.movieappv2.model

import javax.persistence.*
@Entity
data class UserReview(

        @EmbeddedId
        val reviewKey: ReviewKey,

        @ManyToOne
        @MapsId("userId")
        @JoinColumn(name = "user_id")
        val user: User,

        @ManyToOne
        @MapsId("reviewId")
        @JoinColumn(name = "user_id")
        val review: Review,

        val likes: Int,
        val dislikes: Int,
        val rating: Double,
        val totalRating: Int,
)
package com.md.movieappv2.model

import javax.persistence.*

@Entity
data class Rate(

        @EmbeddedId
        val rateKey: RateKey,

        @ManyToOne
        @MapsId("userId")
        @JoinColumn(name = "user_id")
        val user: User,




        val likes: Int,
        val dislikes: Int,
        val rating: Double,
        val totalRating: Int,
       // @ManyToOne
)

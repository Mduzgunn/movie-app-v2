package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Actor @JvmOverloads constructor(
        @Id
        @Column(name = "actor_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val firstName: String,
        val lastName:String,

        @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
        val movie: List<Movie>? = ArrayList()
)

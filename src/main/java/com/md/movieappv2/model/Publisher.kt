package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Publisher @JvmOverloads constructor(
        @Id
        @Column(name = "publisher_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val name: String,
        @OneToOne(mappedBy = "publisher")
        val movie: Movie? = null

)

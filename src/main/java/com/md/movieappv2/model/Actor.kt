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
        val movies: List<Movie>?=ArrayList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Actor

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        return result
    }
}

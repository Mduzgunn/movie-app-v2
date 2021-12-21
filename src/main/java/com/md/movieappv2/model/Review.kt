package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Review @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",

        val movieId: Int,
        val rate: Double,
        val creationDate: LocalDateTime = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user", referencedColumnName = "id")
        val user: User,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Review

        if (id != other.id) return false
        if (movieId != other.movieId) return false
        if (rate != other.rate) return false
        if (creationDate != other.creationDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + movieId
        result = 31 * result + rate.hashCode()
        result = 31 * result + creationDate.hashCode()
        return result
    }
}

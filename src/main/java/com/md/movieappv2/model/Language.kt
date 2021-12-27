package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Language @JvmOverloads constructor(
        @Id
        @Column(name = "language_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val name:String,

        @ManyToMany(mappedBy = "languages", fetch = FetchType.LAZY)
        val movies: List<Movie>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Language

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        return result
    }
}

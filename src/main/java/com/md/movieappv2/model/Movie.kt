package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Movie @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val name: String,
        val releaseYear: Int,
        val description: String,
        val duration: Int,
        val media: String,
        val creationDate: LocalDateTime = LocalDateTime.now(),
        val updatedDate: LocalDateTime = LocalDateTime.now(),
        val isActive: Boolean,

        @field:ElementCollection(fetch = FetchType.EAGER)
        val genresTypes: List<Genre>,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "actor_movies",
                joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "movie_id")],
                inverseJoinColumns = [JoinColumn(name = "actor_id", referencedColumnName = "actor_id")]
        )
        val actors: List<Actor>,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "director_id", referencedColumnName = "director_id")
        val director: Director,

        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
        val publisher: Publisher

)
enum class Genre {
    COMEDY, DRAMA, HORROR, ROMANCE, FANTASY, ACTION, MYSTERY, SCI_FI, THRILLER
}

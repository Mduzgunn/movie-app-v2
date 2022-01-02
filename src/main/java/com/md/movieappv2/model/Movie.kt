package com.md.movieappv2.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Movie @JvmOverloads constructor(
//        name: String,
//        releaseYear: Int,
//        description: String,
//        duration: Int,
//        media: String,
//        active: Boolean,
//        genre: Genre,
//        actorList: MutableList<Actor>,
//        director: Director,
//        publisher: Publisher
        @Id
        @Column(name = "movie_id")
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val name: String,
        val releaseYear: Int,
        val description: String,
        val duration: Int,
        val media: String,
        val isActive: Boolean,
        val genre: Genre,
        val creationDate: LocalDateTime = LocalDateTime.now(),
        val updatedDate: LocalDateTime = LocalDateTime.now(),


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
        val publisher: Publisher,

        @OneToMany
        @JoinTable(
                name = "review_movies",
                joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "movie_id")],
                inverseJoinColumns = [JoinColumn(name = "review_id", referencedColumnName = "review_id")]
        )
        val reviews: List<Review>?,


        val language: Language
//        @ManyToMany(fetch = FetchType.LAZY)
//        @JoinTable(
//                name = "language_movies",
//                joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "movie_id")],
//                inverseJoinColumns = [JoinColumn(name = "language_id", referencedColumnName = "language_id")]
//        )
//        val languages: List<Language>?,


        )
enum class Genre {
    COMEDY, DRAMA, HORROR
}
enum class Language {
        TR, DE, EN
}


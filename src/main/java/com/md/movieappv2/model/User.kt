package com.md.movieappv2.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val username: String,
        val email: String,
        val creationDate: LocalDateTime = LocalDateTime.now(),
        val updatedDate: LocalDateTime = LocalDateTime.now(),

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        val comment: List<Review>,

)    {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
                other as User

                return id != null && id == other.id
        }

        override fun hashCode(): Int = javaClass.hashCode()
}

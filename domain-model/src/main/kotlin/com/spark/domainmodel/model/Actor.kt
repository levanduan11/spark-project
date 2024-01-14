package com.spark.domainmodel.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "actor")
data class Actor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val actorId: Int,
        var firstName: String,
        var lastName: String,
        var lastUpdate: LocalDateTime
)

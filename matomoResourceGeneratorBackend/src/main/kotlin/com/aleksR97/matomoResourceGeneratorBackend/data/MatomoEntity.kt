package com.aleksR97.matomoResourceGeneratorBackend.data;

import jakarta.persistence.*

@Entity
@Table(name = "_matomos")
data class MatomoEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val namespace: String,
    val host: String
)




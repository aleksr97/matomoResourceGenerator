package com.aleksR97.matomoResourceGeneratorBackend.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
data class MatomoEntity (
    @Id @GeneratedValue
    val id: Long,
    val name: String,
    val namespace: String,
    val host: String
)




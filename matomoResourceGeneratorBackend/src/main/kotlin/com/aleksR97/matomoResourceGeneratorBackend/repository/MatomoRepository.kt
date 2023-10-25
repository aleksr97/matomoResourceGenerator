package com.aleksR97.matomoResourceGeneratorBackend.repository

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntityRequest
import org.springframework.data.jpa.repository.JpaRepository

interface MatomoRepository : JpaRepository<MatomoEntityRequest, Long> {
    fun existsByNameAndNamespace(name: String, namespace: String) : Boolean
}
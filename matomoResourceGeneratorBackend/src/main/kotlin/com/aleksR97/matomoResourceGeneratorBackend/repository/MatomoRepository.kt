package com.aleksR97.matomoResourceGeneratorBackend.repository

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.xml.stream.events.Namespace

interface MatomoRepository : JpaRepository<MatomoEntity, Long> {
    fun existsByNameAndNamespace(name: String, namespace: String) : Boolean
}
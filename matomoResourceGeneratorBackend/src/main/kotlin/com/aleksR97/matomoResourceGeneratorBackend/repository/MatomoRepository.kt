package com.aleksR97.matomoResourceGeneratorBackend.repository

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MatomoRepository : JpaRepository<MatomoEntity, Long>
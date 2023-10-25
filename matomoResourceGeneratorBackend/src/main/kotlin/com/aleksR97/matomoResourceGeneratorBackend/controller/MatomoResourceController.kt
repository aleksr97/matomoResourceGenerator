package com.aleksR97.matomoResourceGeneratorBackend.controller

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntity
import com.aleksR97.matomoResourceGeneratorBackend.service.MatomoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/matomo")
class MatomoResourceController (private val matomoService: MatomoService) {

    @PostMapping
    fun createMatomoResource(@RequestBody matomo: MatomoEntity) {
        matomoService.createMatomoResource(matomo)
    }

    @GetMapping("/{id}")
    fun getMatomoResourceById(@PathVariable id: Long): MatomoEntity? {
        return matomoService.getMatomoResourceById(id);
    }

    @GetMapping
    fun getMatomoResourceById() : List<MatomoEntity>?{
        return matomoService.getAllMatomoResources();
    }

}
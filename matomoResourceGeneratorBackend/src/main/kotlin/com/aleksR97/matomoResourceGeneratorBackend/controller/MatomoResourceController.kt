package com.aleksR97.matomoResourceGeneratorBackend.controller

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntityRequest
import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoResponse
import com.aleksR97.matomoResourceGeneratorBackend.data.Metadata
import com.aleksR97.matomoResourceGeneratorBackend.data.Spec
import com.aleksR97.matomoResourceGeneratorBackend.service.MatomoService
import org.springframework.http.ResponseEntity
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
    fun createMatomoResource(@RequestBody matomo: MatomoEntityRequest) : ResponseEntity<MatomoResponse>{
        val apiVersion = "glasskube.eu/v1alpha1"
        val kind = "Matomo"

        matomoService.createMatomoResource(matomo)

        val response = MatomoResponse(
            apiVersion = apiVersion,
            kind = kind,
            metadata = Metadata(matomo.name, matomo.namespace),
            spec = Spec(host = matomo.host)
        )
        return ResponseEntity.ok(response)

    }

    @GetMapping("/{id}")
    fun getMatomoResourceById(@PathVariable id: Long): MatomoEntityRequest? {
        return matomoService.getMatomoResourceById(id)
    }

    @GetMapping
    fun getMatomoResourceById() : List<MatomoEntityRequest>?{
        return matomoService.getAllMatomoResources()
    }

}
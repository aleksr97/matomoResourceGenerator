package com.aleksR97.matomoResourceGeneratorBackend.service

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntityRequest
import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoResponse
import com.aleksR97.matomoResourceGeneratorBackend.data.Metadata
import com.aleksR97.matomoResourceGeneratorBackend.data.Spec
import com.aleksR97.matomoResourceGeneratorBackend.exceptions.ResourceAlreadyExistsException
import com.aleksR97.matomoResourceGeneratorBackend.repository.MatomoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class MatomoService(private val matomoRepository: MatomoRepository) {

    @Value("\${resourceGenerator.apiVersion:DefaultVersion}")
    private lateinit var apiVersion: String
    @Value("\${resourceGenerator.kind:DefaultKind}")
    private lateinit var kind: String
    @PostConstruct
    fun init() {
        println("apiVersion: $apiVersion")
        println("kind: $kind")
    }
    fun getMatomoResourceById(id: Long): MatomoEntityRequest? {
        return matomoRepository.findById(id).orElse(null)
    }
    fun getAllMatomoResources() = matomoRepository.findAll().toList()
    fun matomoResourceExistsInNamespace(name: String, namespace: String): Boolean {
        return matomoRepository.existsByNameAndNamespace(name, namespace)
    }
    fun createMatomoResource(matomo: MatomoEntityRequest): String {
        val name = matomo.name
        val namespace = matomo.namespace

        if (matomoResourceExistsInNamespace(name, namespace)) {
            throw ResourceAlreadyExistsException("Resource with the same name and namespace already exists.")
        }
        matomoRepository.save(matomo)

        return createResourceFile(matomo)
    }

    private fun createResourceFile(matomo: MatomoEntityRequest): String {
        val matomoResponseJSON = createMatomoResponse(matomo)
        val convertedResponse = messageConverter(matomoResponseJSON)
        val filename = matomoResponseJSON.metadata.name
        val directoryPath = "src/main/resources/matomoResources"
        val filePath = "$directoryPath/$filename.yaml" // Include the file extension
        val directory = File(directoryPath)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        File(filePath).writeText(convertedResponse)
        return convertedResponse
    }

    private fun messageConverter(matomoResponseJSON: MatomoResponse): String {
        val objectMapper = ObjectMapper(YAMLFactory())
        return objectMapper.writeValueAsString(matomoResponseJSON)
    }

    private fun createMatomoResponse(matomo: MatomoEntityRequest): MatomoResponse {
        return  MatomoResponse(
            apiVersion = apiVersion,
            kind = kind,
            metadata = Metadata(matomo.name, matomo.namespace),
            spec = Spec(host = matomo.host)
        )
    }



}
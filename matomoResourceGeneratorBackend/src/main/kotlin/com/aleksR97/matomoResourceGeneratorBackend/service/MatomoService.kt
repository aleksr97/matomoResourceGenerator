package com.aleksR97.matomoResourceGeneratorBackend.service

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntityRequest
import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoResponse
import com.aleksR97.matomoResourceGeneratorBackend.exceptions.ResourceAlreadyExistsException
import com.aleksR97.matomoResourceGeneratorBackend.repository.MatomoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
class MatomoService(private val matomoRepository: MatomoRepository) {



    fun createMatomoResource (matomo : MatomoEntityRequest) : MatomoEntityRequest {
        val name = matomo.name
        val namespace = matomo.namespace

        if (matomoResourceExistsInNamespace(name, namespace)) {
            // Resource already exists, so you can throw an exception or return a response.
            throw ResourceAlreadyExistsException("Resource with the same name and namespace already exists.")
        }
        return matomoRepository.save(matomo)
    }

    fun createResourceFile (matomoResource : MatomoResponse) : String{
        val objectMapper = ObjectMapper(YAMLFactory())
        val yamlContent = objectMapper.writeValueAsString(matomoResource)

        val filename = matomoResource.metadata.name
        val directoryPath = "src/main/resources/matomoResources"
        val filePath = "$directoryPath/$filename.yaml" // Include the file extension

        val directory = File(directoryPath)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        File(filePath).writeText(yamlContent)
        println(directory)

        return yamlContent
    }

    fun getMatomoResourceById (id: Long) : MatomoEntityRequest? {
       return matomoRepository.findById(id).orElse(null)
    }

    fun getAllMatomoResources() = matomoRepository.findAll().toList();

    fun matomoResourceExistsInNamespace(name: String, namespace: String) : Boolean{
        return matomoRepository.existsByNameAndNamespace(name, namespace);
    }

}
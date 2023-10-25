package com.aleksR97.matomoResourceGeneratorBackend.service

import com.aleksR97.matomoResourceGeneratorBackend.data.MatomoEntity
import com.aleksR97.matomoResourceGeneratorBackend.exceptions.ResourceAlreadyExistsException
import com.aleksR97.matomoResourceGeneratorBackend.repository.MatomoRepository
import org.springframework.stereotype.Service

@Service
class MatomoService(private val matomoRepository: MatomoRepository) {

    fun createMatomoResource (matomo : MatomoEntity) : MatomoEntity {
        val name = matomo.name
        val namespace = matomo.namespace

        if (matomoResourceExistsInNamespace(name, namespace)) {
            // Resource already exists, so you can throw an exception or return a response.
            throw ResourceAlreadyExistsException("Resource with the same name and namespace already exists.")
        }
        return matomoRepository.save(matomo)
    }

    fun getMatomoResourceById (id: Long) : MatomoEntity? {
       return matomoRepository.findById(id).orElse(null)
    }

    fun getAllMatomoResources() = matomoRepository.findAll().toList();

    fun matomoResourceExistsInNamespace(name: String, namespace: String) : Boolean{
        return matomoRepository.existsByNameAndNamespace(name, namespace);
    }

}
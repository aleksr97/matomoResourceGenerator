package com.aleksR97.matomoResourceGeneratorBackend.data


data class MatomoResponse (
    val apiVersion: String,
    val kind: String,
    val metadata: Metadata,
    val spec: Spec
    )


    data class Metadata(
        val name: String,
        val namespace: String
    )

    data class Spec(
        val host: String
    )
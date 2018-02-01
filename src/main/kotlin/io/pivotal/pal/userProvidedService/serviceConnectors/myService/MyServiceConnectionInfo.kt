package io.pivotal.pal.userProvidedService.serviceConnectors.myService

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.net.URI


data class MyServiceConnectionInfo(val username: String, val password: String, val uri: URI)

fun serviceCredentialsFor(contents: String, serviceName: String): Map<String, String> {
    val objectMapper = ObjectMapper().registerKotlinModule()

    val root = objectMapper.readTree(contents)

    val userProvidedServices = root.path("user-provided")

    val selectedService = userProvidedServices
            .first { it["name"].asText() == serviceName }

    return objectMapper.convertValue(selectedService["credentials"])
}

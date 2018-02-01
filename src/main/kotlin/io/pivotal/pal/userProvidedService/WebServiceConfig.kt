package io.pivotal.pal.userProvidedService

import io.pivotal.pal.userProvidedService.serviceConnectors.myService.MyServiceConnectionInfo
import io.pivotal.pal.userProvidedService.serviceConnectors.myService.serviceCredentialsFor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI


@Configuration
class WebServiceConfig(@Value("\${vcap.services}") val vcapServiceContents: String) {
    @Bean
    fun myServiceConnectionInfo(): MyServiceConnectionInfo {
        val info = serviceCredentialsFor(vcapServiceContents, "my-service")
        return MyServiceConnectionInfo(
                info.getOrDefault("username", "user"),
                info.getOrDefault("password", "password"),
                URI.create(info.getOrDefault("url", "http://my-service.io"))
        )
    }

    // Now that you have connection info, you can also create something like a DataSource
    // or a http client using this information and returning that as a @Bean
}

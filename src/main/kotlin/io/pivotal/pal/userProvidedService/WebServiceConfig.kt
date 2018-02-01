package io.pivotal.pal.userProvidedService

import io.pivotal.pal.userProvidedService.serviceConnectors.myService.MyServiceConnectionInfo
import org.springframework.cloud.config.java.AbstractCloudConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class WebServiceConfig: AbstractCloudConfig() {

    @Bean
    fun myServiceConnectionInfo(): MyServiceConnectionInfo =
            cloud().getServiceInfo("my-service") as MyServiceConnectionInfo
}

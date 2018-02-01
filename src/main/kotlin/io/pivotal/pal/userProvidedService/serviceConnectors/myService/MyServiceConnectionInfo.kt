package io.pivotal.pal.userProvidedService.serviceConnectors.myService

import org.springframework.cloud.service.BaseServiceInfo
import java.net.URI


data class MyServiceConnectionInfo(val username: String, val password: String, val uri: URI): BaseServiceInfo("my-service")

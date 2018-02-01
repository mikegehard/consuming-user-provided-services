package io.pivotal.pal.userProvidedService.serviceConnectors.myService.cloudfoundry

import io.pivotal.pal.userProvidedService.serviceConnectors.myService.MyServiceConnectionInfo
import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator
import org.springframework.cloud.cloudfoundry.Tags
import java.net.URI


class ServiceInfoCreator : CloudFoundryServiceInfoCreator<MyServiceConnectionInfo>(Tags()) {

    private val NAME = "my-service"

    override fun createServiceInfo(serviceData: MutableMap<String, Any>?): MyServiceConnectionInfo {
        val credentials = serviceData?.get("credentials") as MutableMap<*, *>
        val username: String = credentials["username"] as String
        val password: String = credentials["password"] as String
        val uri: String = credentials["url"] as String

        return MyServiceConnectionInfo(
                username,
                password,
                URI.create(uri)
        )
    }

    override fun accept(serviceData: MutableMap<String, Any>?): Boolean {
        return super.accept(serviceData) || nameMatchesTag(serviceData)
    }

    private fun nameMatchesTag(serviceData: MutableMap<String, Any>?): Boolean =
        serviceData?.get("name") == NAME
}

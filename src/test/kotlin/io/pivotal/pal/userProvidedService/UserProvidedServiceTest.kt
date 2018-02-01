package io.pivotal.pal.userProvidedService

import io.pivotal.pal.userProvidedService.serviceConnectors.myService.serviceCredentialsFor
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class UserProvidedServiceTest {

    @Test
    fun canFindInfoForAService() {
        val vcapServices = """
{
    "user-provided": [
        {
            "credentials": {
                "password": "pa55woRD",
                "url": "http://example.com",
                "username": "admin"
            },
            "label": "user-provided",
            "name": "my-service",
            "syslog_drain_url": "",
            "tags": [],
            "volume_mounts": []
        },
        {
        "credentials": {
         "password": "bar",
         "url": "http://example.com",
         "username": "foo"
        },
        "label": "user-provided",
        "name": "another-service",
        "syslog_drain_url": "",
        "tags": [],
        "volume_mounts": []
       }
    ]
}
        """

        val expected = mapOf(
                "username" to "admin",
                "password" to "pa55woRD",
                "url" to "http://example.com"
        )

        val actual = serviceCredentialsFor(vcapServices, "my-service")

        assertThat(actual["username"]).isEqualTo(expected["username"])
        assertThat(actual["password"]).isEqualTo(expected["password"])
        assertThat(actual["url"]).isEqualTo(expected["url"])
    }
}

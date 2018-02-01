package io.pivotal.pal.userProvidedService

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun homepageLoads() {
        // Need to set three environment variables. Values in properties files do not work.
        // These are set in the build.gradle file.
        // Optimally they would be set here so they would be closer to the test code but I couldn't
        // figure out how to set env variables in tests.
        // Tried http://stefanbirkner.github.io/system-rules/ but couldn't get it to work.
        // VCAP_SERVICES - this needs to be set to a json object for the connectors to work.
        // VCAP_APPLICATION - this just needs to exist for the CF connector to kick in
        // SPRING_PROFILES_ACTIVE=cloud - this forces the cloud connectors to kick in
        val result = testRestTemplate.getForObject<String>("/")

        val expected =
                """{"username":"admin","password":"pa55woRD","uri":"http://example.com","id":"my-service"}"""

        assertThat(result).isEqualTo(expected)
    }
}

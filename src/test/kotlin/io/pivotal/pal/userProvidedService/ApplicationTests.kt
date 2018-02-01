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
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun homepageLoads() {
        val result = testRestTemplate.getForObject<String>("/")

        assertThat(result).isEqualTo("""{"hello":"world"}""")
    }
}

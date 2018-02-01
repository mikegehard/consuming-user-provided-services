package io.pivotal.pal.userProvidedService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeController {

    @GetMapping("/")
    fun homepage(): Map<String, String> {
        return mapOf("hello" to "world")
    }
}

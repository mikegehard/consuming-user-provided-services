package io.pivotal.pal.userProvidedService

import io.pivotal.pal.userProvidedService.serviceConnectors.myService.MyServiceConnectionInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeController(@Autowired val myServiceConnectionInfo: MyServiceConnectionInfo) {

    @GetMapping("/")
    fun homepage(): MyServiceConnectionInfo =
            myServiceConnectionInfo
}

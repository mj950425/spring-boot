package tobyspring.helloboot

import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloService: HelloService) {
    @GetMapping("/hello")
    fun hello(name: String): String {
        return helloService.sayHello(name)
    }
}
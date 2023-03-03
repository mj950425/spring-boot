package tobyspring.helloboot

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import tobyspring.config.MySpringBootApplication

@MySpringBootApplication
class TobyspringApplication

fun main(args: Array<String>) {
    runApplication<TobyspringApplication>(*args)
}

package tobyspring.helloboot

import org.springframework.stereotype.Service

@Service
class SimpleHelloService(val helloRepository: HelloRepository) : HelloService {
    override fun sayHello(name: String): String {
        helloRepository.increaseCount(name)
        return "Hello $name"
    }
}
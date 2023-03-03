package tobyspring.helloboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TobyspringApplication::class])
@TestPropertySource("classpath:/application.properties")
@Transactional
class HelloRepositoryTest {

    @BeforeEach
    fun init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)")
    }

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var helloRepository: HelloRepository

    @Test
    fun findHelloFailed() {
        assertThat(helloRepository.findHello("Toby")).isNull()
    }

    @Test
    fun increaseCount() {
        helloRepository.increaseCount("Toby")
        assertThat(helloRepository.findHello("Toby")?.count).isEqualTo(1)
        helloRepository.increaseCount("Toby")
        assertThat(helloRepository.findHello("Toby")?.count).isEqualTo(2)
    }
}
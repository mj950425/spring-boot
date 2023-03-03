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
class JdbcTemplateTest {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)")
    }

    @Test
    fun insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "Toby",3)
        jdbcTemplate.update("insert into hello values(?, ?)", "Spring",1)

        val count = jdbcTemplate.queryForObject("select count(*) from hello", Int::class.java)
        assertThat(count).isEqualTo(2)
    }
}
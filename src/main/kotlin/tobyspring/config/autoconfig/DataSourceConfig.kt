package tobyspring.config.autoconfig

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import tobyspring.config.ConditionalMyOnClass
import tobyspring.config.EnableMyConfigurationProperties
import tobyspring.config.MyAutoConfiguration
import java.sql.Driver
import javax.sql.DataSource

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties::class)
@EnableTransactionManagement
class DataSourceConfig {
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    fun hikariDataSource(properties: MyDataSourceProperties): DataSource {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = properties.url
        dataSource.username = properties.username
        dataSource.password = properties.password
        dataSource.driverClassName = properties.driverClassName
        return dataSource
    }

    @Bean
    @ConditionalOnMissingBean
    fun dataSource(properties: MyDataSourceProperties): DataSource {
        val dataSource = SimpleDriverDataSource()
        dataSource.url = properties.url
        dataSource.username = properties.username
        dataSource.password = properties.password
        dataSource.driver = Class.forName(properties.driverClassName).getConstructor().newInstance() as Driver?
        return dataSource
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource::class)
    @ConditionalOnMissingBean
    fun jdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    @ConditionalOnMissingBean
    fun jdbcTransactionManager(dataSource: DataSource): PlatformTransactionManager {
        return JdbcTransactionManager(dataSource)
    }
}
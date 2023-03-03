package tobyspring.config.autoconfig

import tobyspring.config.MyConfigurationProperties

@MyConfigurationProperties(prefix = "data")
data class MyDataSourceProperties(
    val driverClassName: String? = null,
    val url: String? = null,
    val username: String? = null,
    val password: String? = null,
)

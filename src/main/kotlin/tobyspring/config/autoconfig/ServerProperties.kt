package tobyspring.config.autoconfig

import tobyspring.config.MyConfigurationProperties


@MyConfigurationProperties(prefix = "server")
data class ServerProperties(val port: String? = null, val contextPath: String? = null)

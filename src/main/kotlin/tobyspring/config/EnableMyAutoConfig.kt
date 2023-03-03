package tobyspring.config

import org.springframework.context.annotation.Import
import tobyspring.config.autoconfig.DispatcherServletConfig
import tobyspring.config.autoconfig.TomcatWebServerConfig

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Import(MyAutoConfigImportSelector::class)
annotation class EnableMyAutoConfig

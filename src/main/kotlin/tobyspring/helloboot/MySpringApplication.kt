package tobyspring.helloboot

import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class MySpringApplication {
    companion object{
        internal inline fun <reified T : kotlin.Any> run(args:Array<String>) {
            val applicationContext = object : AnnotationConfigWebApplicationContext() {
                override fun onRefresh() {
                    super.onRefresh()

                    val serverFactory = this.getBean(ServletWebServerFactory::class.java)
                    val dispatcherServlet = this.getBean(DispatcherServlet::class.java)

                    val webServer =
                        serverFactory.getWebServer({ servletContext ->
                            servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*")
                        })
                    webServer.start()
                }

                @Suppress("ACCIDENTAL_OVERRIDE")
                override fun setClassLoader(classLoader: ClassLoader) {
                    this.classLoader = classLoader
                }
            }

            applicationContext.apply {
                register(T::class.java)
                refresh()
            }
        }
    }
}
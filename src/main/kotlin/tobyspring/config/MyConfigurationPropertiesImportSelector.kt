package tobyspring.config

import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyConfigurationPropertiesImportSelector : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val attr = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties::class.java.name)
        val propertyClass = attr!!.getFirst("kClass") as Class<*>
        return arrayOf(propertyClass.name)
    }
}

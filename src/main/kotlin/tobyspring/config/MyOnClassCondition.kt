package tobyspring.config

import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils


class MyOnClassCondition : org.springframework.context.annotation.Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val attrs = metadata.getAllAnnotationAttributes(ConditionalMyOnClass::class.java.name)
        val value = attrs?.get("value")?.get(0) as String
        return ClassUtils.isPresent(value, context.classLoader)
    }
}

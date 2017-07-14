package gr.avalanche.mongobatch.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom AOP Annotation To Log Methods' Execution Time
 * Applicable On ElementType.METHOD, Available To The JVM At Runtime
 * @author nkanakis
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecutionTime {

}
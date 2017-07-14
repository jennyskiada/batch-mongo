package gr.avalanche.mongobatch.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.springframework.stereotype.Component;

/**
 * This module will capture the cross-cutting concern of method execution time logging.
 * @author nkanakis
 */
@Aspect
@Component
public class LogExecutionTimeAspect {

    /**
     * We have annotated our method with @Around. This is our advice, and around
     * advice means we are adding extra code both before and after method execution.
     * Our @Around annotation has a point cut argument. Our pointcut just says,
     * ‘Apply this advice any method which is annotated with @LogExecutionTime.’
     */
    @Around("@annotation(gr.avalanche.mongobatch.aop.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final Slf4JStopWatch stopWatch = new Slf4JStopWatch();
        stopWatch.start(((MethodSignature) joinPoint.getSignature()).getMethod().getName());
        //Just call the annotated method (Join point)
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        return proceed;
    }
}

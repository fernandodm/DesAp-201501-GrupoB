package ar.edu.unq.desapp.groupb.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

    Logger logger;
    
    @Around("execution( * ar.edu.unq.desapp.groupb.services..*(..))")
    public Object logException(ProceedingJoinPoint method) throws Throwable{
        try {            
                return method.proceed();
        } catch (Exception e) {
            logger = Logger.getLogger(method.getTarget().getClass());
            logger.error(e);
            throw new Exception();
        }
    }
}
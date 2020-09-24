package maktab.aspect;

import maktab.model.entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);
    @Around("@annotation(maktab.aspect.log)")
    public User logg(ProceedingJoinPoint proceedingJoinPoint) {

        try {
           User user = (User) proceedingJoinPoint.proceed();
            logger.debug(user.getUsername()+" logged in successfully.");
            return user;
        } catch (Throwable throwable) {
            logger.debug("login failed.");
        }
        return null;
    }

    @Before("allServiceMethods() && allAddMethods()")
    public void beforeAddMethodsLog(JoinPoint joinPoint){
        logger.debug(joinPoint.getTarget().toString()+" wants to add.");
    }

    @Pointcut("within(maktab.service.*))")
    public void allServiceMethods() {
    }


    @Pointcut("execution(public * add*(*))")
    public void allAddMethods() {
    }


}

package co.in.ankitbansal.springboot.angular7.mongo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by ankit on 3/4/17.
 */
@Slf4j
@Component
@Aspect
public class PerformanceLogger {

  @Around("execution(* co.in.ankitbansal.springboot.angular7.mongo.service.*.*(..))")
  public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object retVal = joinPoint.proceed();

    stopWatch.stop();

    StringBuilder logMessage = new StringBuilder();
    logMessage.append(joinPoint.getTarget().getClass().getName());
    logMessage.append(".");
    logMessage.append(joinPoint.getSignature().getName());
    logMessage.append(" execution time: ");
    logMessage.append(stopWatch.getTotalTimeMillis());
    logMessage.append(" ms");

    log.info(logMessage.toString());

    if ( log.isDebugEnabled() && joinPoint.getArgs().length > 0 ) {

      StringBuilder debugMessage = new StringBuilder();
      debugMessage.append( "Method arguments for " );
      debugMessage.append( joinPoint.getTarget().getClass().getName() );
      debugMessage.append( "." );
      debugMessage.append( joinPoint.getSignature().getName() );
      debugMessage.append( " (" );
      Object[] args = joinPoint.getArgs();
      for (int i = 0; i < args.length; i++) {
        debugMessage.append(args[i]).append(", ");
      }
      debugMessage.deleteCharAt(debugMessage.length() - 2);

      debugMessage.append(")");
      log.debug( debugMessage.toString() );
    }

    return retVal;
  }

}
package co.in.ankitbansal.springboot.angular7.mongo.aspect;

/**
 * Created by ankit on 3/4/17.
 */

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

  @AfterThrowing(pointcut = "within(co.in.ankitbansal.springboot.angular7.mongo.controller..*)", throwing = "e")
  public void logException(JoinPoint joinPoint, Throwable e) {

    Signature signature = joinPoint.getSignature();
    String methodName = signature.getName();
    String stuff = signature.toString();
    String arguments = Arrays.toString(joinPoint.getArgs());


    log.error("Exception In Method : "
        + methodName + " with arguments "
        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
        + e.getMessage());
  }

}
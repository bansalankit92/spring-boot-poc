package co.in.ankitbansal.springboot.angular7.mongo.log.annotation;

import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface Trace {
  LogEventType type() default LogEventType.REQUEST;
}

package co.in.ankitbansal.springboot.angular7.mongo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by ankit on 3/4/17.
 */
@Getter
@Setter
public class RestAPIException extends RuntimeException {

  private final HttpStatus errorCode;

  public RestAPIException(HttpStatus errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}

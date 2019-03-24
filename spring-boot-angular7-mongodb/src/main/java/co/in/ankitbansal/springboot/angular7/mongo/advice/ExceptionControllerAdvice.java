package co.in.ankitbansal.springboot.angular7.mongo.advice;

import co.in.ankitbansal.springboot.angular7.mongo.exception.RestAPIException;
import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.util.appconstants.ErrorMessages;
import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

  /*
   * catching all unhandled exceptions from Controllers
   */
  @ExceptionHandler(Exception.class)
  @Trace(type = LogEventType.EXCEPTION)
  public ResponseEntity<RestErrorResponse> processException(Exception ex) {
    log.error("Exception caught: ", ex);

    return new ResponseEntity<>(
        new RestErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * method to handle HttpMessageNotReadableException
   */
  @ExceptionHandler(value = {HttpMessageNotReadableException.class,
      MissingServletRequestParameterException.class})
  @Trace(type = LogEventType.EXCEPTION)
  private ResponseEntity<RestErrorResponse> processBadRequestException(Exception ex) {
    log.error(ex.getClass().getName() + " caught", ex);
    return new ResponseEntity<>(
        new RestErrorResponse(HttpStatus.BAD_REQUEST,
            ex.getMessage()), HttpStatus.BAD_REQUEST);

  }

  /**
   * Method to handle RestAPIException
   */
  @ExceptionHandler(RestAPIException.class)
  @Trace(type = LogEventType.EXCEPTION)
  private ResponseEntity<RestErrorResponse> processRestAPIException(RestAPIException ex) {
    log.error("RestAPIException caught: ", ex);
    if (ex.getErrorCode().equals(HttpStatus.NOT_FOUND)) {
      return new ResponseEntity<>(
            new RestErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()),
            HttpStatus.NOT_FOUND);
    } else if (ex.getErrorCode().equals(HttpStatus.BAD_REQUEST)) {
      return new ResponseEntity<>(
            new RestErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),
            HttpStatus.BAD_REQUEST);
    } else {
      log.error(ErrorMessages.INTERNAL_SERVER_ERROR, ex);
      return new ResponseEntity<>(new RestErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Catching all HttpRequestMethodNotSupportedException when unsupported method Rest call is made
   */
  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  @Trace(type = LogEventType.EXCEPTION)
  public ResponseEntity<RestErrorResponse> unsupportedHttpMethodCallHandling(
      HttpRequestMethodNotSupportedException ex) {
    log.error("HttpRequestMethodNotSupportedException caught: ", ex);
    String errorInfo = ex.getMethod()
        + ErrorMessages.ERROR_INFO_UNSUPPORTED_METHOD
        + ex.getSupportedHttpMethods().toString();
    RestErrorResponse restError =
        new RestErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, errorInfo);
    return new ResponseEntity<>(restError, HttpStatus.METHOD_NOT_ALLOWED);
  }

}

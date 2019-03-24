package co.in.ankitbansal.springboot.angular7.mongo.util;

import co.in.ankitbansal.springboot.angular7.mongo.exception.RestAPIException;
import co.in.ankitbansal.springboot.angular7.mongo.util.appconstants.ErrorMessages;
import co.in.ankitbansal.springboot.angular7.mongo.util.appconstants.InfoMessages;
import java.util.Locale;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

@Slf4j
public class CommonUtils {

  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private CommonUtils() {

  }

  public static String randomAlphaNumeric(int count) {
    StringBuilder builder = new StringBuilder();
    Random random = new Random();
    while (count-- != 0) {
      int character = random.nextInt(ALPHA_NUMERIC_STRING.length());
      builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
  }

  public static String getReferralCode() {
    return randomAlphaNumeric(6);
  }

  public static void recordNotFoundException(String key, String value,
      MessageSource messageSource) {
    String message = messageSource.getMessage(ErrorMessages.NO_RECORD_FOUND,
        new Object[]{key, value}, Locale.ENGLISH);
    log.info(message);
    throw new RestAPIException(HttpStatus.NOT_FOUND, message);
  }

  public static void allFieldsMissingException(MessageSource messageSource) {
    String message = messageSource.getMessage(ErrorMessages.FIELD_MISSING,
        new Object[]{"All"}, Locale.ENGLISH);
    log.info(message);
    throw new RestAPIException(HttpStatus.BAD_REQUEST, message);
  }

  public static void fieldsMissingException(MessageSource messageSource, String fields) {
    String message = messageSource.getMessage(ErrorMessages.FIELD_MISSING,
        new Object[]{fields}, Locale.ENGLISH);
    log.info(message);
    throw new RestAPIException(HttpStatus.BAD_REQUEST, message);
  }

  public static void recordAlreadyExistException(String key, String value,
      MessageSource messageSource) {
    String message = messageSource.getMessage(ErrorMessages.RECORD_ALREADY_EXISTS,
        new Object[]{key, value}, Locale.ENGLISH);
    log.info(message);
    throw new RestAPIException(HttpStatus.CONFLICT, message);
  }

  public static void recordFoundLog(String key, String value, MessageSource messageSource) {
    log.info(messageSource
        .getMessage(InfoMessages.DATA_FOUND,
            new Object[]{key, value}, Locale.ENGLISH));
  }

}

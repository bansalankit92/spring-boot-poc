package co.in.ankitbansal.springboot.angular7.mongo.util.validation;


import co.in.ankitbansal.springboot.angular7.mongo.exception.RestAPIException;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.util.appconstants.ErrorMessages;
import java.util.Locale;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

/**
 * Created by ankit on 3/4/18.
 */
@Slf4j
public class UserValidator {

  private UserValidator() {

  }

  public static void validateCreate(User user, MessageSource messageSource) {
    if (Objects.isNull(user)) {
      throwRestAPIException("All User Fields ", messageSource);
    }
    if (Objects.isNull(user.getMobile()) && Objects.isNull(user.getEmailId())) {
      throwRestAPIException("MobileNo. or email atleast is needed", messageSource);
    }
    //@TODO validate emailid and mobileNo

  }

  public static void validateSave(User user, MessageSource messageSource) {
    if (Objects.isNull(user.getId())) {
      throwRestAPIException("ID.", messageSource);
    }
//    if(Objects.isNull(user.getMobile())){
//      throwRestAPIException("MobileNo.",messageSource);
//    }
    if (Objects.isNull(user.getEmailId())) {
      throwRestAPIException("EmailId.", messageSource);
    }
  }

  private static void throwRestAPIException(String field, MessageSource messageSource) {
    String message = messageSource
        .getMessage(ErrorMessages.FIELD_MISSING,
            new Object[]{field}, Locale.ENGLISH);
    throw new RestAPIException(HttpStatus.BAD_REQUEST, message);
  }

  private static void throwInvalidInputRestAPIException(String field, MessageSource messageSource) {
    String message = messageSource
        .getMessage(ErrorMessages.INVALID_INPUT,
            new Object[]{field}, Locale.ENGLISH);
    throw new RestAPIException(HttpStatus.BAD_REQUEST, message);
  }


}

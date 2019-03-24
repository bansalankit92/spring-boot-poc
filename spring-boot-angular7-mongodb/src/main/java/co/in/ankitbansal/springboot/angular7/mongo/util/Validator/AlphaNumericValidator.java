package co.in.ankitbansal.springboot.angular7.mongo.util.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by ankit on 15/4/17.
 */
public class AlphaNumericValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return String.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    String text = (String) target;
    if (!StringUtils.isAlphanumeric(text)) {
      errors.reject("id.malformed");
    }
  }
}

package co.in.ankitbansal.springboot.angular7.mongo.util.Validator;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by ankit on 15/4/17.
 */
@Component
public class DigitValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Long.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    String id = (String) target;
    if (!NumberUtils.isCreatable(id)) {
      errors.reject("id.malformed");
    }
  }
}

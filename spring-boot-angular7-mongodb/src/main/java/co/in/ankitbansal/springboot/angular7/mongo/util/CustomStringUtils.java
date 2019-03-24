package co.in.ankitbansal.springboot.angular7.mongo.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

/**
 * Created by ankit on 3/4/17.
 */
@Slf4j
public class CustomStringUtils {

  public static String toUnderscoreAlphanumeric(final String string, final int length) {
    String newString = string.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll(" ", "_").toLowerCase();
    return newString
        .substring(0, newString.length() < length ? newString.length() : length);
  }

  public static String toUnderscoreAlphanumeric(final String string) {
    return toUnderscoreAlphanumeric(string, string.length());
  }

  public static String getOnlyDigits(String s) {
    Pattern pattern = Pattern.compile("[^0-9]");
    Matcher matcher = pattern.matcher(s);
    return matcher.replaceAll("");
  }

  public static String getOnlyStrings(String s) {
    Pattern pattern = Pattern.compile("[^a-z A-Z]");
    Matcher matcher = pattern.matcher(s);
    return matcher.replaceAll("");
  }

  public static String getUniqueName(String key) {
    return new Date().getTime() + (Strings.isNotEmpty(key) ? "_" + key.replaceAll(" ", "_")
        .toLowerCase() : "");
  }

}

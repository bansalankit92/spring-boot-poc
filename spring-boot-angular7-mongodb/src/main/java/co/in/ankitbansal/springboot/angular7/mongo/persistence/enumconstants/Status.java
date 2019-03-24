package co.in.ankitbansal.springboot.angular7.mongo.persistence.enumconstants;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

/**
 * Created by ankit on 4/4/17.
 */
public enum Status {

  CREATED("created"),
  EDITED("edited"),
  UNVERIFIED("unverified"),
  VERIFIED("verified"),
  DELETED("deleted");

  @Getter
  private final String status;

  Status(String status) {
    this.status = status;
  }

  public static boolean checkValueExist(String value) {
    for (Status a : values()) {
      if (a.status.equals(value)) {
        return true;
      }
    }
    return false;
  }

  private static Map<String, Status> FORMAT_MAP = Stream
      .of(Status.values())
      .collect(Collectors.toMap(s -> s.status, Function.identity()));

  @JsonCreator
  public static Status fromString(String string) {
    return Optional
        .ofNullable(FORMAT_MAP.get(string))
        .orElse(Status.CREATED);
  }
}

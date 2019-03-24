package co.in.ankitbansal.springboot.angular7.mongo.persistence.dto;

import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

  private String username;
  private String fullName;
  private String profilePicURL;
  private String facebookId;
  private String instagramId;
  private String twitterId;
  private String linkedinId;
  private String googleplusId;
  private String snapchatId;

  public static UserDetailsDto from(User user) {
    UserDetailsDto userDetailsDto = null;
    if (Objects.nonNull(user)) {
      userDetailsDto = new UserDetailsDto();
      BeanUtils.copyProperties(user, userDetailsDto);
    }
    return userDetailsDto;
  }

}

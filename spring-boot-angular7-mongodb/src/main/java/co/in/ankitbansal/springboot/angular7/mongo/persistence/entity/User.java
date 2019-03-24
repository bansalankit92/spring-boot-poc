package co.in.ankitbansal.springboot.angular7.mongo.persistence.entity;

import co.in.ankitbansal.springboot.angular7.mongo.persistence.enumconstants.Status;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String id;
  private String username;
  private String fullName;
  private String gender;
  private String mobile;
  private String emailId;
  private String profilePicURL;
  private String otp;
  private String facebookId;
  private String instagramId;
  private String twitterId;
  private String linkedinId;
  private String googleplusId;
  private String snapchatId;
  private Status isMobileVerified;
  private Status isEmailVerified;
  @CreatedDate
  private Date createdDate;
  @LastModifiedDate
  private Date updatedDate;

}
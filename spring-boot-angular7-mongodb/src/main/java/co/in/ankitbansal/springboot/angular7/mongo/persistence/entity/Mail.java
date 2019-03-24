package co.in.ankitbansal.springboot.angular7.mongo.persistence.entity;


import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

  @NotNull
  private String from;

  @NotNull
  private String to;

  private String subject;

  private boolean isHtml;

  private String message;

  @CreatedDate
  private Date createdDate ;

  @LastModifiedDate
  private Date updatedDate;
}

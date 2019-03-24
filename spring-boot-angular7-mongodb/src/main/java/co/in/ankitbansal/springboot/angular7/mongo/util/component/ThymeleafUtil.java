package co.in.ankitbansal.springboot.angular7.mongo.util.component;

import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class ThymeleafUtil {

  private static TemplateEngine templateEngine;

  @Autowired
  private TemplateEngine tempTemplateEngine;

  public static String getHtml(User user, String templateName) {

    Context context = new Context();

    if (Objects.nonNull(user)) {
      Map<Object, Object> dataMap;
      ObjectMapper mapper = new ObjectMapper();
      dataMap = mapper.convertValue(user, new TypeReference<Map>() {
      });
      context.setVariable("user", dataMap);
      return templateEngine.process(templateName, context);
    }
    return "";

  }



  @PostConstruct
  void init() {
    templateEngine = tempTemplateEngine;
  }
}

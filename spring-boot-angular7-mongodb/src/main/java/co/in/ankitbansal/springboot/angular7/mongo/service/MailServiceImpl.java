package co.in.ankitbansal.springboot.angular7.mongo.service;


import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.Mail;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.util.component.ThymeleafUtil;
import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.Mail;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.util.component.ThymeleafUtil;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String from;

  @Value("${mail.new.user.subject}")
  private String newUserSubject;


  @Autowired
  public MailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public void sendNewUserMail(User user) {
    if (Objects.nonNull(user) && Strings.isNotEmpty(user.getEmailId())) {
      Mail mail = new Mail();
      mail.setTo(user.getEmailId());
      mail.setFrom(from);
      mail.setSubject("Thank you for using our service!");
      mail.setMessage(ThymeleafUtil.getHtml(user,"newUser"));
      mail.setHtml(true);
      sendActualMail(mail);
    }
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public Mail sendMail(Mail mail) {
    sendActualMail(mail);
    return (mail);
  }

  private void sendActualMail(Mail mail) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom(from);
      messageHelper.setTo(mail.getTo());
      messageHelper.setSubject(mail.getSubject());
      messageHelper.setText(mail.getMessage(),mail.isHtml());
    };
    try {
      mailSender.send(messagePreparator);
    } catch (MailException e) {
      log.error("Error occurred while sending mail to : {} , error:{}", mail.getTo(),
          e.getMessage());
    }
  }

}

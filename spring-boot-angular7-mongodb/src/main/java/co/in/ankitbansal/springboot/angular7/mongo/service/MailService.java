package co.in.ankitbansal.springboot.angular7.mongo.service;


import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.Mail;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.Mail;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;

public interface MailService {
  void sendNewUserMail(User user);

  Mail sendMail(Mail mail);

}

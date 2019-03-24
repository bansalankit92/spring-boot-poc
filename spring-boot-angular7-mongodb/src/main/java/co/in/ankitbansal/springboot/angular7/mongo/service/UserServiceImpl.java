package co.in.ankitbansal.springboot.angular7.mongo.service;


import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.dto.UserDetailsDto;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.repo.UserRepo;
import co.in.ankitbansal.springboot.angular7.mongo.util.CommonUtils;
import co.in.ankitbansal.springboot.angular7.mongo.util.CustomStringUtils;
import co.in.ankitbansal.springboot.angular7.mongo.util.appconstants.AppConstants;
import co.in.ankitbansal.springboot.angular7.mongo.util.validation.UserValidator;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by ankit on 26/7/17.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final MailService mailService;
  private UserRepo userRepo;
  private MessageSource messageSource;

  @Autowired
  public UserServiceImpl(UserRepo userRepo, MessageSource messageSource,
      MailService mailService) {
    this.userRepo = userRepo;
    this.messageSource = messageSource;
    this.mailService = mailService;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public List<User> findAll() {
    return userRepo.findAll();
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepo.findAll(pageable);
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User findById(String id) {
    User user = Strings.isNotEmpty(id) ? userRepo.findById(id).orElse(null) : null;
    if (Objects.isNull(user)) {
      userIsEmpty(id);
    } else {
      userAvailable(user.getId());
    }
    return user;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User findByMobile(String mobile) {
    User user = userRepo.findByMobile(mobile);
    if (Objects.isNull(user)) {
      userIsEmpty(" mobile No: " + mobile);
    } else {
      userAvailable(user.getId());
    }
    return user;
  }


  @Trace(type = LogEventType.SERVICE)
  @Override
  public User findByEmailId(String emailId) {
    User user = Strings.isNotEmpty(emailId) ? userRepo.findByEmailId(emailId) : null;
    if (Objects.isNull(user)) {
      userIsEmpty(" Email Id : " + emailId);
    } else {
      userAvailable(user.getId());
    }
    return user;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User findByUsername(String username) {
    User user = Strings.isNotEmpty(username) ? userRepo.findByUsername(username) : null;
    if (Objects.isNull(user)) {
      userIsEmpty(" username : " + username);
    } else {
      userAvailable(user.getId());
    }
    return user;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public UserDetailsDto getUserDetailsByUsername(String username) {
    User user = Strings.isNotEmpty(username) ? userRepo.findByUsername(username) : null;
    if (Objects.isNull(user)) {
      userIsEmpty(" username : " + username);
    } else {
      userAvailable(user.getId());
      return UserDetailsDto.from(user);
    }
    return null;
  }

  @Override
  public boolean isUsernameAvailable(String username) {
    return Objects.nonNull(userRepo.findByUsername(username));
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User add(User user) {
    UserValidator.validateCreate(user, messageSource);
    User newUser = null;
    if (isExist(user.getId())) {
      CommonUtils.recordAlreadyExistException(AppConstants.ID, user.getId(), messageSource);
    } else {
      isEmailOrMobileExist(user);
      String username = CustomStringUtils.getOnlyStrings(user.getEmailId().split("@")[0]);
      user.setUsername(username);
      newUser = userRepo.insert(user);
      // send a mail to user
      // mailService.sendNewUserMail(newUser);
    }
    return newUser;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User update(User user) {
    User updatedUser = null;
    UserValidator.validateSave(user, messageSource);
    User oldUser = findById(user.getId());
    if (Objects.nonNull(oldUser)) {
      updatedUser = userRepo.save(user);
    } else {
      userIsEmpty(null);
    }
    return updatedUser;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public User delete(String id) {
    log.error("Trying to delete User by id {}", id);
    User user = null;
    if (Strings.isNotEmpty(id)) {
      user = userRepo.findById(id).orElse(null);
      if (!Objects.isNull(user)) {
        userRepo.delete(user);
      }
    }
    return user;
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public Boolean isEmailIdExist(String emailId) {
    return Objects.nonNull(findByEmailId(emailId));
  }

  @Trace(type = LogEventType.SERVICE)
  @Override
  public Boolean isMobileExist(String mobile) {
    return Objects.nonNull(findByMobile(mobile));
  }


  @Trace(type = LogEventType.SERVICE)
  @Override
  public Page<User> findByUsernameOrFullName(String query, Pageable pageable) {
    if (Strings.isNotEmpty(query)) {
      return userRepo.findByUsernameOrFullName(query, pageable);
    } else {
      return findAll(pageable);
    }
  }

  private boolean isExist(String id) {
    return Strings.isNotEmpty(id) && userRepo.existsById(id);
  }


  private void userIsEmpty(String id) {
    CommonUtils.recordNotFoundException(AppConstants.ID, id, messageSource);
  }

  private void userAvailable(String id) {
    CommonUtils.recordFoundLog(AppConstants.ID, id, messageSource);
  }

  private void isEmailOrMobileExist(User user) {
    try {
      User oldUser = findByEmailId(user.getEmailId());
      if (Objects.nonNull(oldUser)) {
        CommonUtils
            .recordAlreadyExistException(AppConstants.EMAIL_ID, user.getEmailId(), messageSource);
      }
    } catch (Exception ex) {
      log.error("User not found with email id");
    }
    try {
      User oldUser1 = findByMobile(user.getMobile());
      if (Objects.nonNull(oldUser1)) {
        CommonUtils
            .recordAlreadyExistException(AppConstants.MOBILE, user.getMobile() + "", messageSource);
      }
    } catch (Exception ex) {
      log.error("User not found with mobile");
    }
  }


}

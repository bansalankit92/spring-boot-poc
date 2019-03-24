package co.in.ankitbansal.springboot.angular7.mongo.service;

import co.in.ankitbansal.springboot.angular7.mongo.persistence.dto.UserDetailsDto;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.dto.UserDetailsDto;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

  List<User> findAll();

  Page<User> findAll(Pageable pageable);

  User findById(String id);

  User findByMobile(String mobile);

  User findByEmailId(String emailId);

  User findByUsername(String username);

  UserDetailsDto getUserDetailsByUsername(String username);

  boolean isUsernameAvailable(String username);

  User add(User user);

  User update(User user);

  User delete(String id);

  Boolean isEmailIdExist(String emailId);

  Boolean isMobileExist(String mobile);

  Page<User> findByUsernameOrFullName(String query, Pageable pageable);

}

package co.in.ankitbansal.springboot.angular7.mongo.persistence.repo;

import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepo extends MongoRepository<User,String> {

  User findByMobile(String mobile);
  User findByEmailId(String email);
  User findByFullName(String fullName);

  User findByUsername(String username);

  Page<User> findByUsernameLikeOrFullNameLike(String username, String fullName, Pageable pageable);

  default Page<User> findByUsernameOrFullName(String query, Pageable pageable) {
    return findByUsernameLikeOrFullNameLike(query, query, pageable);
  }
}

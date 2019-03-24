package co.in.ankitbansal.springboot.angular7.mongo.controller;

import co.in.ankitbansal.springboot.angular7.mongo.log.annotation.Trace;
import co.in.ankitbansal.springboot.angular7.mongo.log.eventtype.LogEventType;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.dto.UserDetailsDto;
import co.in.ankitbansal.springboot.angular7.mongo.persistence.entity.User;
import co.in.ankitbansal.springboot.angular7.mongo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "saveUser", nickname = "saveUser")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = User.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  public ResponseEntity<User> save(@RequestBody User user) {
    User savedUser = userService.add(user);
    return ResponseEntity.status(HttpStatus.OK).body(savedUser);
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "getUserById", nickname = "getUserById")
  @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = User.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    User user = userService
        .findById(userId);
    return ResponseEntity.ok(user);
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "getUsersByUsernameOrFullName", nickname = "getUsersByUsernameOrFullName")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = User.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiImplicitParams({
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  public ResponseEntity<Page<User>> getUsersByUsernameOrFullName(
      @RequestParam(value = "q", required = false) String query, Pageable pageable) {
    Page<User> user = userService
        .findByUsernameOrFullName(query, pageable);
    return ResponseEntity.ok(user);
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "updateUser", nickname = "updateUser")
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = User.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  public ResponseEntity<User> update(@RequestBody User user) {
    User updatedUser = userService.update(user);
    return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "getUserByMobileNo", nickname = "getUserByMobileNo")
  @GetMapping(path = "/{mobileNo}/mobile", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = User.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  public ResponseEntity<User> getUserByMobileNo(@PathVariable("mobileNo") String mobileNo) {
    User user = userService
        .findByMobile(mobileNo);
    return ResponseEntity.ok(user);
  }

  @Trace(type = LogEventType.CONTROLLER)
  @ApiOperation(value = "getUserDetailsByUsername", nickname = "getUserDetailsByUsername")
  @GetMapping(path = "/{username}/username", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = UserDetailsDto.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  public ResponseEntity<UserDetailsDto> getUserDetailsByUsername(
      @PathVariable("username") String username) {
    UserDetailsDto user = userService
        .getUserDetailsByUsername(username);
    return ResponseEntity.ok(user);
  }

//
//  @Trace(type = LogEventType.CONTROLLER)
//  @ApiOperation(value = "uploadFileByUsername", nickname = "uploadFileByUsername")
//  @PostMapping(path = "/{username}/upload", produces = MediaType.APPLICATION_JSON_VALUE)
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = FileDetails.class),
//      @ApiResponse(code = 401, message = "Unauthorized"),
//      @ApiResponse(code = 403, message = "Forbidden"),
//      @ApiResponse(code = 404, message = "Not Found"),
//      @ApiResponse(code = 500, message = "Failure")})
//  public ResponseEntity<FileDetails> uploadFile(@PathVariable("username") String username,
//      @RequestParam("file") MultipartFile file) {
//    FileDetails fileDetails = fileDetailsService.upload(username, file);
//    return ResponseEntity.ok(fileDetails);
//  }


}

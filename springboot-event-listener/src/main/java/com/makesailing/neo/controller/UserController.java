package com.makesailing.neo.controller;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 8:59
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public String register(@RequestBody UserEntity userEntity) {
    userService.register(userEntity);
    return "注册成功";
  }
}

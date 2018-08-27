package com.makesailing.neo.controller;

import com.makesailing.neo.domain.UserEntity;
import com.makesailing.neo.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 22:59
 */
@RestController
@RequestMapping("/user")
public class LoginConroller {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public String login(@RequestBody UserEntity userEntity,HttpServletRequest request) {
    UserEntity entity = userRepository.findUserEntityByNameAndPwd(userEntity.getName(),userEntity.getPwd());
    if (Objects.nonNull(entity)) {
      // 将用户信息写入session
      request.getSession().setAttribute("user_session", entity);
      return "登录成功";
    }

    return "用户名或密码错误";
  }
}

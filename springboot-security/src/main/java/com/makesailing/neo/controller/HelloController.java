package com.makesailing.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/10 22:14
 */
@Controller
public class HelloController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("login")
  public String login() {
    return "login";
  }
}

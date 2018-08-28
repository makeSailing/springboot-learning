package com.makesailing.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 23:20
 */
@Controller
@RequestMapping("/user")
public class IndexController {

  @GetMapping("/login_view")
  public String loginView() {
    return "login";
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }

}

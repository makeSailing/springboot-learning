package com.makesailing.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/22 21:32
 */
@Controller
public class IndexController {

  @GetMapping("/index")
  public String index() {
    return "index";
  }
}

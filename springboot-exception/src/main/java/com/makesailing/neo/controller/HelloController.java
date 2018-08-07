package com.makesailing.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/7 22:39
 */
@Controller
public class HelloController {

   @GetMapping("/hello")
   public String getHello() throws Exception{
      throw new Exception("发生未知错误");
   }
}

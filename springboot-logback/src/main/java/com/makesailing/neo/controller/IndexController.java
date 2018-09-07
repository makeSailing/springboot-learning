package com.makesailing.neo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/7 21:35
 */
@RestController
@Slf4j
public class IndexController {

  @GetMapping("/log")
  public String index() {
    log.info("info 日志");
    log.debug("debug 日志");
    log.error("error 日志");
    return "index";
  }
}

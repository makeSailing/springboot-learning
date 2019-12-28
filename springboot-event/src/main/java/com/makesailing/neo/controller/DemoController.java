package com.makesailing.neo.controller;

import com.makesailing.neo.domain.MessageEntity;
import com.makesailing.neo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟触发事件
 *
 * @author oKong
 */
@RestController
@RequestMapping("/push")
@Slf4j
public class DemoController {

    /**
     * 注入 Sping 上下文
     */
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping
    public String push(String code, String message) {
        log.info("发布applicationEvent事件:{},{}", code, message);
        applicationContext
            .publishEvent(new CustomEvent(this, MessageEntity.builder().code(code).message(message).build()));
        return "事件发布成功!";
    }

    @GetMapping("/obj")
    public String pushObject(String code, String message) {
        log.info("发布对象事件:{},{}", code, message);
        applicationContext.publishEvent(MessageEntity.builder().code(code).message(message).build());
        return "对象事件发布成功!";
    }
}
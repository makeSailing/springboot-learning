package com.makesailing.neo.listener.impl;

import com.makesailing.neo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2019/6/21 11:50
 */
@Component
@Slf4j
public class CustomListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        //这里也可以监听所有事件 使用  ApplicationEvent 类即可
        //这里仅仅监听自定义事件 CustomEvent
        log.info("ApplicationListener方式监听事件：{}", event);
    }
}

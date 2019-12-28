package com.makesailing.neo.listener.annotation;

import com.makesailing.neo.domain.MessageEntity;
import com.makesailing.neo.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2019/6/21 11:43
 */
@Configuration
@Slf4j
public class EventListenerConfig {

    @EventListener
    public void handleEvent(Object event) {
        // 监听所有事件
        log.info("事件 : {}", event);
    }

    @EventListener
    public void handleCustomEvent(CustomEvent customEvent) {
        //监听 CustomEvent事件
        log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }

    /**
     * 监听 code为oKong的事件
     */
    @EventListener(condition = "#customEvent.messageEntity.code == 'oKong'")
    public void handleCustomEventByCondition(CustomEvent customEvent) {
        //监听 CustomEvent事件
        log.info("监听到code为'oKong'的CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(),
            customEvent.getTimestamp());
    }

    @EventListener
    public void handleObjectEvent(MessageEntity messageEntity) {
        //这个和eventbus post方法一样了
        log.info("监听到对象事件，消息为：{}", messageEntity);

    }


}

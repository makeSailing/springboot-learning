package com.makesailing.neo.event;

import com.makesailing.neo.domain.MessageEntity;
import org.springframework.context.ApplicationEvent;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2019/6/21 11:36
 */
public class CustomEvent extends ApplicationEvent {

    private MessageEntity messageEntity;

    public CustomEvent(Object obj, MessageEntity messageEntity) {
        super(obj);
        this.messageEntity = messageEntity;
    }

    public MessageEntity getMessageEntity() {
        return this.messageEntity;
    }

}

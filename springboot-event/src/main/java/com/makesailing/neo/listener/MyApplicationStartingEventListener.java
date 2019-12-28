package com.makesailing.neo.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2019/6/21 14:39
 */
public class MyApplicationStartingEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("ApplicationStartingEvent事件发布 :　" + event.getTimestamp());
    }
}

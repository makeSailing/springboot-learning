package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import com.makesailing.neo.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 用户注册成功监听,发送邮件
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:13
 */
@Component
public class UserRegisterSendMailListener implements SmartApplicationListener {

  @Override
  public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
    return eventType == UserRegisterEvent.class;
  }

  @Override
  public boolean supportsSourceType(Class<?> sourceType) {
    return sourceType == UserService.class;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    try {
      Thread.sleep(10000);//静静的沉睡3秒钟
    }catch (Exception e){
      e.printStackTrace();
    }
    UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;

    UserEntity userEntity = userRegisterEvent.getUserEntity();

    System.out.println("用户 : " + userEntity.getName() + " , 注册成功,发送邮件通知");
  }

  @Override
  public int getOrder() {
    return 1;
  }
}

package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import com.makesailing.neo.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 用户注册 > 保存用户信息监听
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:02
 */
@Component
public class UserRegisterListener implements SmartApplicationListener {

  /**
   * 该方法返回true&supportSourceType同样返回true时,才会调用该监听内的onApplicationEvent方法
   * @param eventType
   * @return
   */
  @Override
  public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
    // 只有 UserRegisterEvent 监听类型才有执行下面的逻辑
    return eventType == UserRegisterEvent.class;
  }

  /**
   * 该方法返回true&supportEventType同样返回true时,才会调用该监听内的onApplicationEvent方法
   */
  @Override
  public boolean supportsSourceType(Class<?> sourceType) {
    // 只有在 UserService 发布的 UserRegisterEvent 才会执行下面的逻辑
    return sourceType == UserService.class;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    // 转换事件类型
    UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
    // 获取注册用户对象信息
    UserEntity userEntity = userRegisterEvent.getUserEntity();

    System.out.println("实现 SmartApplicationListener 监听 注册信息  用户名 : " + userEntity.getName() + " , 密码 : " + userEntity.getPassword() );
  }

  /**
   * 同步情况下监听执行顺序
   * @return
   */
  @Override
  public int getOrder() {
    return 0;
  }
}

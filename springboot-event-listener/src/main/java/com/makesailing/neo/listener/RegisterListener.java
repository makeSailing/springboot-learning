package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;

/**
 * # 原始方法实现事件监听
 *   用户注册监听
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:48
 */
//@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

  /**
   * 实现监听
   * @param event
   */
  @Override
  public void onApplicationEvent(UserRegisterEvent event) {
    UserEntity userEntity = event.getUserEntity();

    // .. 省略逻辑

    System.out.println("实现 ApplicationListener 监听 注册信息  用户名 : " + userEntity.getName() + " , 密码 : " + userEntity.getPassword() );
  }
}

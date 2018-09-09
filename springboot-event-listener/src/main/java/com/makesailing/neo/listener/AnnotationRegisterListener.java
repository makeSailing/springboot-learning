package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;

/**
 * # 使用@EventListener 方法实现注册事件监听
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:02
 */
//@Component
public class AnnotationRegisterListener {

  @EventListener
  public void register(UserRegisterEvent userRegisterEvent) {
      // 获取用户对象
    UserEntity userEntity = userRegisterEvent.getUserEntity();

    // .. 省略逻辑

    //输出用户信息
    System.out.println("@EventListener 注册信息  用户名 : " + userEntity.getName() + " ,密码 : " + userEntity.getPassword() );
  }
}

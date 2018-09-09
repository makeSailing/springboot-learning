package com.makesailing.neo.listener;

import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;

/**
 * # 注册用户,发送成功邮件
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:59
 */
//@Component
public class RegisterUserEmailListener {

  @EventListener
  public void sendEmail(UserRegisterEvent userRegisterEvent) {
    System.out.println("注册成功 , 发送邮件");
  }
}

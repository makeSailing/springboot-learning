package com.makesailing.neo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/10 22:25
 */
@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
          .antMatchers("/", "/home").permitAll()
          .anyRequest().authenticated()
          .and()
        .formLogin() //定义当需要用户登录时候，转到的登录页面
          .loginPage("/login")
          .permitAll()
          .and()
        .logout()
          .permitAll();
  }

  // 在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
  }
}

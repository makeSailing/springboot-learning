package com.makesailing.neo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 23:29
 */
@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
  }
}

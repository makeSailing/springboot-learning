package com.makesailing.neo.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 23:23
 */
public class SessionInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
      throws Exception {
    // 登录不作拦截
    if (Objects.equals(httpServletRequest.getRequestURL(), "/user/login") ||
        Objects.equals(httpServletRequest.getRequestURL(), "/user/login_view")) {
      return true;
    }
    // 验证 session是否存在
    if (Objects.isNull(httpServletRequest.getSession().getAttribute("user_session"))) {
      httpServletResponse.sendRedirect("/user/login_view");
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
      ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
      Exception e) throws Exception {

  }
}

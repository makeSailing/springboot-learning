package com.makesailing.neo.exctption.handler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * # 异常处理类
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/7 22:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  public static final String DEFAULT_ERROR_VIEW = "error";

  @ExceptionHandler(value = Exception.class)
  public ModelAndView defaultExecptionHandler(HttpServletRequest request, Exception e) throws Exception {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", e);
    modelAndView.addObject("url", request.getRequestURL());
    modelAndView.setViewName(DEFAULT_ERROR_VIEW);
    return modelAndView;
  }
}

package com.makesailing.neo.domain;

import java.io.Serializable;
import java.util.List;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/28 21:34
 */
public class MailEntity implements Serializable {

  /**
   * SMTP服务器
   */
  private String smtpService;

  /**
   * SMTP 端口
   */
  private String smtpPort;

  /**
   * 设置发送邮箱
   */
  private String fromMailAddress;

  /**
   * 发送邮箱的SMTP口令
   */
  private String fromMailSmtpPwd;

  /**
   * 邮件标题
   */
  private String title;

  /**
   * 邮件内容
   */
  private String content;

  /**
   * 内容格式 采用html
   */
  private String contentType;

  /**
   * 接受邮件地址集合
   */
  private List<String> addresss;
}

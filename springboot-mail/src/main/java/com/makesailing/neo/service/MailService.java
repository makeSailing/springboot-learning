package com.makesailing.neo.service;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/28 22:25
 */
public interface MailService {

  String SERVICE_ID = "mailService";

  void sendSimpleMail(String to, String subject, String content);
}

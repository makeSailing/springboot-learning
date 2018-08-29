package com.makesailing.neo.service.impl;
import com.makesailing.neo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/28 22:26
 */
@Slf4j
@Service(MailService.SERVICE_ID)
public class MailServiceImpl implements MailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${mail.fromMail.addr}")
  private String from;

  @Override
  public void sendSimpleMail(String to, String subject, String content) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(from);
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(content);

    try {
      mailSender.send(simpleMailMessage);
      log.info("简单邮件已经发送。");
    } catch (Exception e) {
      log.error("发送简单邮件时发生异常！", e);
    }
  }
}

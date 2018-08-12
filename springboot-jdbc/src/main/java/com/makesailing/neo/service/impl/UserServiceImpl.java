package com.makesailing.neo.service.impl;

import com.makesailing.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/10 23:02
 */
@Service(UserService.SERIVCE_ID)
public class UserServiceImpl implements UserService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void create(String name, Integer age) {
    jdbcTemplate.update("insert into user (name ,age) values (?,?)", name, age);
  }

  @Override
  public void deleteByName(String name) {
    jdbcTemplate.update("delete from user where name = ?", name);
  }

  @Override
  public Integer getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from user", Integer.class);
  }

  @Override
  public void deleteAllUsers() {
    jdbcTemplate.update("delete from user ");
  }
}

package com.makesailing.neo;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.repository.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

  @Test
  public void contextLoads() {
  }

  @Autowired
  private UserMapper userMapper;

  @Test
  @Rollback
  public  void testGetUserByName() {
    userMapper.insertUser("jack", 30);
    User user = userMapper.getUserByName("jack");

    Assert.assertEquals(30,user.getAge().intValue());
  }
}

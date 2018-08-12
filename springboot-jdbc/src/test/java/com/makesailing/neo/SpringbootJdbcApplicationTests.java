package com.makesailing.neo;

import com.makesailing.neo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJdbcApplicationTests {

  @Autowired
  private UserService userService;

  @Before
  public void setUp() {
    // 准备,清空 user 表
    userService.deleteAllUsers();
  }

  @Test
  public void contextLoads() {
  }

  @Test
  public void userTest() {

    userService.create("jamie", 18);
    userService.create("tom", 20);
    userService.create("jack", 22);
    userService.create("bin", 24);
    userService.create("jason", 26);

    // 查询数据库,应有 5 条记录
    Assert.assertEquals(5, userService.getAllUsers().intValue());

    // 删除2个用户,还剩下3个
    userService.deleteByName("tom");
    userService.deleteByName("bin");

    Assert.assertEquals(3,userService.getAllUsers().intValue());
  }

}

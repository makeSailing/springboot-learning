package com.makesailing.neo;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.repository.UserMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    userMapper.insertUserByParam("jack", 30, "12345");

    Map<String, Object> map = new HashMap<>();
	  map.put("name", "张三");
	  map.put("age", 18);
	  map.put("pwd", "123456");
	userMapper.insertUserByMap(map);

	  User user = new User();
	  user.setName("李四");
	  user.setAge(20);
	  user.setPwd("123456");
	userMapper.insertUserByObject(user);

	  List<User> jack = userMapper.getUserByName("jack");

    //Assert.assertEquals(30,user.getAge().intValue());
  }
}

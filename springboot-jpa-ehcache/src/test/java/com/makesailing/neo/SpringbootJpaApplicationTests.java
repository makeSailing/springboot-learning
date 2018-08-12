package com.makesailing.neo;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

  @Autowired
  private UserRepository userRepository;

  //@Before
  public void setUp() {
    userRepository.save(new User("tom", 19));
  }

  @Test
  public void testQuery() {
    User u1 = userRepository.findUser("tom");
    System.out.println("第一次查询" + u1.getAge());

    User u2 = userRepository.findUser("tom");
    System.out.println("第二次查询" + u2.getAge());

  }

  @Test
  public void test() throws Exception {

    // 创建10条记录
    userRepository.save(new User("AAA", 10));
    userRepository.save(new User("BBB", 20));
    userRepository.save(new User("CCC", 30));
    userRepository.save(new User("DDD", 40));
    userRepository.save(new User("EEE", 50));
    userRepository.save(new User("FFF", 60));
    userRepository.save(new User("GGG", 70));
    userRepository.save(new User("HHH", 80));
    userRepository.save(new User("III", 90));
    userRepository.save(new User("JJJ", 100));

    // 测试findAll, 查询所有记录
    Assert.assertEquals(10, userRepository.findAll().size());

    // 测试findByName, 查询姓名为FFF的User
    Assert.assertEquals(60, userRepository.findUserByName("FFF").getAge().longValue());

    // 测试findUser, 查询姓名为FFF的User
    Assert.assertEquals(60, userRepository.findUser("FFF").getAge().longValue());

    // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
    Assert.assertEquals("FFF", userRepository.findUserByNameAndAge("FFF", 60).getName());

    // 测试删除姓名为AAA的User
    userRepository.delete(userRepository.findUserByName("AAA"));

    // 测试findAll, 查询所有记录, 验证上面的删除是否成功
    Assert.assertEquals(9, userRepository.findAll().size());

  }
}

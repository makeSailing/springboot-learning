#### springboot-mybatis 整合(annotation版)

##### 0. 代码目录![目录](https://user-gold-cdn.xitu.io/2018/8/29/165839817d7b1e07?w=364&h=502&f=png&s=28723)



##### 1. pom.xml 配置如下:

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.makesailing.neo</groupId>
  <artifactId>springboot-mybatis</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>springboot-mybatis</name>
  <description>Demo project for Spring Boot</description>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.14.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>1.3.0</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.40</version>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
  
</project>
```

##### 2. application.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot
    username: root
    password: abc
    driver-class-name: com.mysql.jdbc.Driver
```

##### 3. User

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 2188774443310292458L;

  private Long id;

  private String name;

  private Integer age;

  private String pwd;
}
```

##### 4.UserMapper

```java
@Mapper
public interface UserMapper {

  @Select("select * from user where name = #{name}")
  List<User> getUserByName(@Param("name") String name);

  // 使用几种不同的传参方式,来实现数的插入

  /**
   *  通过 @Param 来传递参数
   * @param name
   * @param age
   * @param pwd
   * @return
   */
  @Insert("insert into user (name ,age, pwd) values ( #{name},#{age},#{pwd})")
  int insertUserByParam(@Param("name") String name, @Param("age") Integer age,@Param("pwd")String pwd);

  /**
   * 通过 map 来传递参数
   * @param map
   * @return
   */
  @Insert("INSERT INTO user (name,age,pwd) VALUES (#{name,jdbcType=VARCHAR},#{age,jdbcType=TINYINT},#{pwd,jdbcType=VARCHAR})")
  int insertUserByMap(Map<String, Object> map);

  /**
   * 通过 object 对象来传递参数
   * @param user
   * @return
   */
  @Insert("INSERT INTO user (name,age,pwd) values (#{name},#{age},#{pwd})")
  int insertUserByObject(User user);

  /**
   * 根据 id 修改用户信息
   * @param user
   * @return
   */
  @Update("UPDATE user set name = #{name} where id = #{id}")
  int updataUser(User user);

  /**
   *
   * @param id
   */
  @Delete("DELETE FROM user where id = #{id}")
  void deleteById(Long id);

  /**
   * @Result中的property属性对应User对象中的成员名，column对应SELECT出的字段名
   * @return
   */
  @Results({
      @Result(property = "name", column = "name"),
      @Result(property = "age", column = "age")
  })
  @Select("SELECT name, age FROM user")
  List<User> findAll();

}
```

##### 5. 测试

```java
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
```
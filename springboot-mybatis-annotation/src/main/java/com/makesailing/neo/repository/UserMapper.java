package com.makesailing.neo.repository;

import com.makesailing.neo.domain.User;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/12 16:22
 */
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

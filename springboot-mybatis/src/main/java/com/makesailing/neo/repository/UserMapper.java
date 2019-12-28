package com.makesailing.neo.repository;

import com.makesailing.neo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/12 16:22
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User getUserByName(@Param("name") String name);

    @Insert("insert into user (name ,age) values ( #{name},#{age})")
    int insertUser(@Param("name") String name, @Param("age") Integer age);

}

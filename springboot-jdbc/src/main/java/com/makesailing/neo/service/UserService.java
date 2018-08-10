package com.makesailing.neo.service;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/10 23:01
 */

public interface UserService {

  String SERIVCE_ID = "userService";

  /**
   * 新增一个用户
   * @param name
   * @param age
   */
  void create(String name, Integer age);

  /**
   * 根据name删除一个用户高
   * @param name
   */
  void deleteByName(String name);

  /**
   * 获取用户总量
   */
  Integer getAllUsers();

  /**
   * 删除所有用户
   */
  void deleteAllUsers();
}

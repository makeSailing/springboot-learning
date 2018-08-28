package com.makesailing.neo.repository;

import com.makesailing.neo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 23:06
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{

  UserEntity findUserEntityByNameAndPwd(String name, String pwd);
}

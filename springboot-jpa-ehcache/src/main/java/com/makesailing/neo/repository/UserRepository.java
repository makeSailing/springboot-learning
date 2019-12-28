package com.makesailing.neo.repository;

import com.makesailing.neo.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/10 23:25
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByName(String name);

    User findUserByNameAndAge(String name, Integer age);

    //@Cacheable
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}

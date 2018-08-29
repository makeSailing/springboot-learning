package com.makesailing.neo.service;

import com.makesailing.neo.domain.User;
import java.util.List;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/29 14:33
 */
public interface UserService {

	String SERVICE_ID = "userService";

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(User user);

	/**
	 * 查询所有用户
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<User> findAllUser(Integer pageNum, Integer pageSize);

}



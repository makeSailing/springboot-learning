package com.makesailing.neo.service.impl;

import com.github.pagehelper.PageHelper;
import com.makesailing.neo.domain.User;
import com.makesailing.neo.mappers.UserMapper;
import com.makesailing.neo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/29 14:35
 */
@Service(UserService.SERVICE_ID)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int addUser(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public List<User> findAllUser(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		return userMapper.findAllUser();
	}
}



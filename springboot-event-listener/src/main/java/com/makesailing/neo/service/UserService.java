package com.makesailing.neo.service;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:39
 */
@Service
public class UserService {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 用户注册
	 * @param userEntity
	 */
	public void register(UserEntity userEntity) {
		// 省略其他逻辑

		// 发布 userRegisterEvent 事件
		UserRegisterEvent event = new UserRegisterEvent(this, userEntity);
		applicationContext.publishEvent(event);
	}
}



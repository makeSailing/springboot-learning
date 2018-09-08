package com.makesailing.neo.event;

import com.makesailing.neo.entity.UserEntity;
import org.springframework.context.ApplicationEvent;

/**
 * # 用户注册监听事件
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:32
 */
public class UserRegisterEvent extends ApplicationEvent {

	// 注册用户对象
	private UserEntity userEntity;

	/**
	 * Create a new ApplicationEvent.
	 * 重写构造方法
	 *
	 * @param source 发生事件的对象
	 */
	public UserRegisterEvent(Object source, UserEntity userEntity) {
		super(source);
		this.userEntity = userEntity;
	}
}



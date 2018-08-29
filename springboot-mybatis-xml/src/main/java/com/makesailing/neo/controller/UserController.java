package com.makesailing.neo.controller;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/29 14:32
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("add")
	public int addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/all")
	public List<User> findAllUser(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		return userService.findAllUser(pageNo, pageSize);
	}
}



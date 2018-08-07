package com.makesailing.neo.controller;

import com.alibaba.fastjson.JSON;
import com.makesailing.neo.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 *
 * @date 2018/8/7 10:57
 */
@RestController
public class UserController {

	/**
	 * 创建线程安全的 map
	 */
	static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<>());

	@GetMapping("/users")
	public List<User> getUserList() {
		List<User> list = new ArrayList<>(userMap.values());
		return list;
	}

	@PostMapping("/add")
	public String addUser(@RequestBody User user) {
		userMap.put(user.getId(), user);
		Map<String, String> map = new HashMap<>();
		map.put("status", "200");
		map.put("data", JSON.toJSONString(user));
		return JSON.toJSONString(map);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userMap.get(id);
	}

	@PutMapping("/update")
	public String updateUser(@RequestBody User user) {
		User u = userMap.get(user.getId());
		u.setName(user.getName());
		u.setAge(user.getAge());
		return "success";
	}

	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable("id")Long id){
		userMap.remove(id);
		return "success";
	}
}



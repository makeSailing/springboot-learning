package com.makesealing.chapter1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 *
 * @date 2018/8/6 17:31
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String getHello() {
		return "hello world";
	}
}



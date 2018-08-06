package com.makesealing.chapter1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/6 17:31
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String getHello() {
		return "hello world";
	}
}



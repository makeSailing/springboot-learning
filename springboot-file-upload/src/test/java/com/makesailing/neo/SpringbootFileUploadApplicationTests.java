package com.makesailing.neo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFileUploadApplicationTests {

	@Test
	public void contextLoads() {
		Map<String, List<Long>> map = new HashMap<>();
		if (map.isEmpty()) {
			System.out.println("9999");
		}
	}

}

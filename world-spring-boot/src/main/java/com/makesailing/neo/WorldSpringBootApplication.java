package com.makesailing.neo;

import com.makesailing.neo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.makesailing.neo")
public class WorldSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WorldSpringBootApplication.class, args);
    }

    @Autowired
    private GirlFriendService girlFriendService;

    @Autowired
    private PersonService personService;


    @Override
    public void run(String... args) throws Exception {
        // 调用打招呼方法
        girlFriendService.say();
        personService.sayHello();
    }
}

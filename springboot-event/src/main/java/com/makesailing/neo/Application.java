package com.makesailing.neo;

import com.makesailing.neo.listener.MyApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        // 加入自定义的监听类
        springApplication.addListeners(new MyApplicationStartingEventListener());
        springApplication.run(args);
    }

}

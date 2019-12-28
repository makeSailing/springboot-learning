package com.makesailing.neo.service;

import com.makesailing.neo.config.PersonProperties;

public class PersonService {

    private PersonProperties properties;

    public PersonService() {
    }

    public PersonService(PersonProperties properties) {
        this.properties = properties;
    }

    public void sayHello(){
        System.out.println("大家好，我叫: " + properties.getUserName() + ", 今年" + properties.getAge() + "岁"
            + ", 性别: " + properties.getSex());
    }
}

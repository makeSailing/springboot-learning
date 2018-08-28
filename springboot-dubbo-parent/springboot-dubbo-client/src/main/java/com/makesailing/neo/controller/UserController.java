package com.makesailing.neo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.makesailing.neo.domain.User;
import com.makesailing.neo.service.UserService;
import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}/detail")
    public String findOneById(@PathVariable Long id){
        User user = userService.findOneById(id);
        if (Objects.nonNull(user)) {
            return JSON.toJSONString(user);
        }
        return null;
    }

    @GetMapping("/saveOne")
    public int saveOne(){
        User user = new User();
        user.setUserName("lisi");
        user.setRealName("李四");
        user.setParkCode("010101");
        user.setParkName("北海公园");
        user.setPwd("12345");
        user.setState((byte)1);
        user.setRole((byte)1);
        user.setLastDate(new Date());
        user.setLastIp("127.0.0.1");
        user.setLoginCount(12);
        user.setDateAdd(new Date());
        user.setDateUpd(new Date());


        return userService.insertOne(user);
    }

    @GetMapping("/delOne")
    public int delOne(Long id){
        return userService.delById(id);
    }
}
package com.makesailing.neo.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSON;
import com.makesailing.neo.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * UserController Tester.
 *
 * @since <pre>08/07/2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUserController() throws Exception {
        // 获取用户列表,应该为空
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(equalTo("[]")));

        // 新增用户
        User user = new User();
        user.setId(1L);
        user.setName("jamie");
        user.setAge(20);

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(user)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("status", is("200")))
            .andReturn().getResponse().getContentAsString();

        // 获取用户列表,应该有值
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print());

        // 修改用户属性
        User u = new User();
        u.setId(1L);
        u.setName("终极测试大师");
        u.setAge(30);

        mockMvc.perform(put("/update").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(u)))
            .andDo(print());

        //根据 userId ,获取用户信息
        mockMvc.perform(get("/1").contentType(MediaType.APPLICATION_JSON)).andDo(print());
        // 根据 userId , 删除用户
        mockMvc.perform(delete("/1").contentType(MediaType.APPLICATION_JSON)).andDo(print());

        // 获取用户列表,应该为空
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(equalTo("[]")));

    }

    /**
     * Method: getUserList()
     */
    @Test
    public void testGetUserList() throws Exception {
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(equalTo("[]")));
    }

    /**
     * Method: addUser(@RequestBody User user)
     */
    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("jamie");
        user.setAge(20);

        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(user)))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn().getResponse().getContentAsString();

    }

    /**
     * Method: getUserById(@PathVariable("id") Long id)
     */
    @Test
    public void testGetUserById() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: updateUser(@RequestBody User user)
     */
    @Test
    public void testUpdateUser() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: deleteUserById(@PathVariable("id")Long id)
     */
    @Test
    public void testDeleteUserById() throws Exception {
        //TODO: Test goes here...
    }


}

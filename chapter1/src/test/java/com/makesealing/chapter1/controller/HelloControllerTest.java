package com.makesealing.chapter1.controller; 

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * HelloController Tester.
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @since <pre>08/06/2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Method: getHello()
     */
    @Test
    public void testGetHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("hello world")));
    }


}

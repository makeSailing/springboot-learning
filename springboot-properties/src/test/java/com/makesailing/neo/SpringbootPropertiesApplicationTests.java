package com.makesailing.neo;

import com.makesailing.neo.config.BlogPeoperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootPropertiesApplicationTests {

    @Autowired
    private BlogPeoperties blogPeoperties;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getBlogPeoperties() {
        Assert.assertEquals(blogPeoperties.getName(), "jamie");
        Assert.assertEquals(blogPeoperties.getTitle(), "SpringBoot Review");
        System.out.println(blogPeoperties.getDesc());

        System.out.println(blogPeoperties.getValue());

        System.out.println(blogPeoperties.getNummber());

        System.out.println(blogPeoperties.getBigNumber());

        System.out.println(blogPeoperties.getTest1_10());

        System.out.println(blogPeoperties.getTest10_20());
    }


}

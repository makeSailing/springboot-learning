package com.makesailing.neo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/6 22:35
 */
@Data
@Component
public class BlogPeoperties {

    @Value("${com.makesailing.blog.name}")
    private String name;

    @Value("${com.makesailing.blog.title}")
    private String title;

    @Value("${com.makesailing.blog.desc}")
    private String desc;

    /**
     * 随机 String
     */
    @Value("${com.makesailing.blog.value}")
    private String value;

    /**
     * 随机int
     */
    @Value("${com.makesailing.blog.number}")
    private Integer nummber;

    /**
     * 随机 long
     */
    @Value("${com.makesailing.blog.bignumber}")
    private Long bigNumber;

    /**
     * 10以内的随机数
     */
    @Value("${com.makesailing.blog.test1-10}")
    private Integer test1_10;

    /**
     * 10-20 以内的随机数
     */
    @Value("${com.makesailing.blog.test10-20}")
    private Integer test10_20;
}

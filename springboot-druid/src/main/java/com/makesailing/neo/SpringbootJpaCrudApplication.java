package com.makesailing.neo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
//配置druid必须加的注解，如果不加，访问页面打不开，filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
@ComponentScan(basePackages = "com.makesailing.neo")
@EnableAutoConfiguration
public class SpringbootJpaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaCrudApplication.class, args);
    }

    /**
     * FastJson SerializerFeatures
     * WriteNullListAsEmpty ：List字段如果为null,输出为[],而非null
     * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为”“,而非null
     * WriteMapNullValue：是否输出值为null的字段,默认为false。
     * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
     * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
     */
    /**
     * 通过@Bean注解
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {

        //1.定义一个消息转换对象convert
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2.添加fastJson配置信息，是否需要格式化
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty
        );

        //3.在convert添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //4.将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}


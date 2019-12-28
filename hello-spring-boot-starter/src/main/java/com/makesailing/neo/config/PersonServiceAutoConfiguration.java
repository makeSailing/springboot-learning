package com.makesailing.neo.config;

import com.makesailing.neo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * # 总结Starter工作原理
 * 1. SpringBoot 在启动时扫描项目所依赖的JAR包,寻找包含 spring.factories 文件内Jar包
 * 2. 然后读取 spring.factories 文件内获取配置的自动配置类 AutoConfiguration
 * 3. 然后将自动配置类下的满足条件(@ConditionalOnXxx)的@Bean放入到Spring容器中(Spring Context)
 * 4. 这样使用者就可以直接用来注入 ,因为该类已经在容器中
 *
 * @Author: aa
 * @Date: Created in  2019/12/28 12:51
 */
@Configuration
@EnableConfigurationProperties(PersonProperties.class) // 开启使用配置参数,将配置实体作为配置来源
@ConditionalOnClass(PersonService.class)// 在SpringIoc容器内 存在指定的Class条件
@ConditionalOnProperty(prefix = "spring.person", value = "enable", matchIfMissing = true) // 指定的属性是否有指定的值
public class PersonServiceAutoConfiguration {

    @Autowired
    private PersonProperties properties;

    @Bean
    @ConditionalOnMissingBean // 当 SpringIoc容器内不存在此Bean才进行注入
    public PersonService personService() {
        System.out.println("创建  personService Bean -------------------");
        PersonService personService = new PersonService(properties);
        return personService;
    }
}

package com.makesailing.neo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * #
 *
 * @Author: aa
 * @Date: Created in  2019/12/28 11:57
 */
@Data
@ConfigurationProperties(prefix = "spring.person") // 只是将配置文件转换成domain对象,并不会加入IOC容器
public class PersonProperties {

    private String userName;

    private Integer age;

    private String sex;
}

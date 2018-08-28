package com.makesailing.neo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 13:45
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource("classpath:dubbo/*.xml")
public class PropertiesConfig {

}



#### Springboot-druid 配置

application.properties 配置

```properties
# 数据库连接信息
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/springboot
spring.datasource.username=root
spring.datasource.password=abc
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# jpa 配置
spring.jpa.database=mysql
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.strategy=org.hibernate.cfg.DefaultComponentSafeNamingStrategy

# druid 增加配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
# 下面为连接池的补充配置,应用到上面所有数据源中
# 初始化大小 最大,最小
spring.datasource.initialSize=5
spring.datasource.minIdle=5
spring.datasource.maxActive=20
# 配置获取连接等待超时的时间
spring.datasource.maxWait=60000
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
spring.datasource.timeBetweenEvictionRunsMillis=60000
# 配置一个连接在池中最小生存的时间，单位是毫秒
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=SELECT 1 FROM DUAL
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false
# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.poolPreparedStatements=true
spring.datasource.maxPoolPreparedStatementPerConnectionSize=20
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.filters=stat,wall,log4j
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# 合并多个DruidDataSource的监控数据
#spring.datasource.useGlobalDataSourceStat=truespring.datasource.min
```

- DataSource Bean配置

```java
package com.makesailing.neo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * #
 * 配置druid需要的配置类，引入application.properties文件中以spring.datasource开头的信息
 * 因此需要在application.properties文件中配置相关信息
 * @Author: jamie.li
 * @Date: Created in  2018/8/23 22:39
 */
@Configuration
public class DruidConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource druidDataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    return druidDataSource;
  }

  /**
   * 配置druid过滤规则
   * @return
   */
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }

  @Bean
  public ServletRegistrationBean druidServlet() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    // IP白名单
    servletRegistrationBean.addInitParameter("allow", "*");
    // IP黑名单(共同存在时，deny优先于allow)
    servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
    //控制台管理用户
    servletRegistrationBean.addInitParameter("loginUsername", "admin");
    servletRegistrationBean.addInitParameter("loginPassword", "admin");
    //是否能够重置数据 禁用HTML页面上的“Reset All”功能
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }
}
```

- 打开浏览器 [登录] (http://127.0.0.1:8080/druid/login.html)

  输入 配置的账户与密码

  ![image](https://user-images.githubusercontent.com/42613962/44533390-04735d80-a728-11e8-9026-e7c05badb7f3.png)

![image](https://user-images.githubusercontent.com/42613962/44533433-181ec400-a728-11e8-9c38-c96f6e3fe2de.png)


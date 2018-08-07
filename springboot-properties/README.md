#### SpringBoot 的环境配置

- 1. 打 jar 包 : mvn clean install


#####  使用 jar 包的方式启动 程序

java -jar  包名  --server.port:8080 (自定义端口)

#####  多环境配置

application-{profile}.yml

在 application.yml 选择加载那个配置**

```yaml
spring:
  profiles:
    active: dev
```

也可以以 jar 包的方式指定以那个配置启动

java -jar xxx.jar --spring.profiles.active=指定配置文件
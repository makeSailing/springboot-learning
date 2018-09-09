### springboot-mybatis (xml版)

##### 1. pom.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
 <modelVersion>4.0.0</modelVersion>

 <groupId>com.makesailing.neo</groupId>
 <artifactId>springboot-mybatis-xml</artifactId>
 <version>0.0.1-SNAPSHOT</version>
 <packaging>jar</packaging>

 <name>springboot-mybatis-xml</name>
 <description> Spring Boot Mybatis XML</description>

 <parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.5.14.RELEASE</version>
  <relativePath/> <!-- lookup parent from repository -->
 </parent>

 <properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
  <java.version>1.8</java.version>
 </properties>

 <dependencies>
  <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
  </dependency>
  <dependency>
   <groupId>org.mybatis.spring.boot</groupId>
   <artifactId>mybatis-spring-boot-starter</artifactId>
   <version>1.3.2</version>
  </dependency>

  <dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
   <version>5.1.40</version>
  </dependency>
  <dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>fastjson</artifactId>
   <version>1.2.40</version>
  </dependency>
  <dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>druid</artifactId>
   <version>1.0.29</version>
  </dependency>
  <dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <optional>true</optional>
  </dependency>

  <!-- 分页插件 -->
  <dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper-spring-boot-starter</artifactId>
   <version>1.1.2</version>
  </dependency>

 </dependencies>

 <build>
  <plugins>
   <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
   </plugin>

   <!-- mybatis generator 自动生成代码插件 -->
   <plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.2</version>
    <configuration>
     <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
     <overwrite>true</overwrite>
     <verbose>true</verbose>
    </configuration>
   </plugin>
  </plugins>
 </build>


</project>
```

##### 2.采用 mybatis 自动生成代码插件

- 需要在 pom.xml 中的 插件管理中加入这段

  ```xml
  <!-- mybatis generator 自动生成代码插件 -->
  <plugin>
   <groupId>org.mybatis.generator</groupId>
   <artifactId>mybatis-generator-maven-plugin</artifactId>
   <version>1.3.2</version>
   <configuration>
    <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
    <overwrite>true</overwrite>
    <verbose>true</verbose>
   </configuration>
  </plugin>
  ```

- 在 resources 下新建 generator,再新建 generatorConfig.xml . 此路径和上面 pom 插件中的描述地址一致

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE generatorConfiguration PUBLIC
    "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
  <generatorConfiguration>
    <!-- 数据库驱动:选择你的本地硬盘上面的数据库驱动包-->
    <classPathEntry location="E:\mysql-connector-java-5.1.44-bin.jar"/>

    <context id="context" targetRuntime="MyBatis3">
      <commentGenerator>
        <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
        <property name="suppressAllComments" value="true"/>

        <property name="suppressDate" value="true"/>
      </commentGenerator>
  	<!-- 数据库连接信息 -->
      <jdbcConnection userId="root" password="xxx" driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://127.0.0.1:3306/springboot" />

      <javaTypeResolver>
        <property name="forceBigDecimals" value="false"/>
      </javaTypeResolver>

      <!-- 生成模型的包名和位置-->
      <javaModelGenerator targetPackage="com.makesailing.neo.domain" targetProject="src/main/java">
        <property name="enableSubPackages" value="true"/>
        <property name="trimStrings" value="true"/>
      </javaModelGenerator>
      <!-- 生成映射文件的包名和位置-->
      <sqlMapGenerator targetPackage="mappers" targetProject="src/main/resources">
        <property name="enableSubPackages" value="true"/>
      </sqlMapGenerator>
      <!-- 生成DAO的包名和位置-->
      <javaClientGenerator targetPackage="com.makesailing.neo.mappers" type="XMLMAPPER" targetProject="src/main/java">
        <property name="enableSubPackages" value="true"/>
      </javaClientGenerator>
      <!-- 要生成的表 tableName是数据库中的表名或视图名 domainObjectName是实体类名-->
      <table tableName="user" domainObjectName="User" enableCountByExample="false" enableDeleteByExample="false"
        enableSelectByExample="false" enableUpdateByExample="false"/>
    </context>
  </generatorConfiguration>
  ```

- 点击 run ---> Edit Configurations ..

  ![](https://user-gold-cdn.xitu.io/2018/8/29/165848953977fed0?w=541&h=396&f=png&s=60337)

- 添加运行配置 mybatis-generator:generate -e

  ![](https://user-gold-cdn.xitu.io/2018/8/29/165848d6233ee591?w=1115&h=414&f=png&s=67569)

- 运行

  ![](https://user-gold-cdn.xitu.io/2018/8/29/165848f45705b424?w=454&h=181&f=png&s=22993)

- 生成后的文件结构

  ![](https://user-gold-cdn.xitu.io/2018/8/29/165848fde55ce6dc?w=391&h=610&f=png&s=37232)

##### 3. application.yml 配置如下

```yaml
server:
  port: 8080
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/springboot?characterEncoding=UTF-8&rewriteBatchedStatements=true&generateSimpleParameterMetadata=true
    username: root
    password: root
#    使用 druid 数据源
    type: com.alibaba.druid.pool.DruidDataSource
    # 初始化大小 最大,最小
    maxActive: 20
    initialSize: 5
    minIdle: 5
    # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat
    # 配置获取连接等待超时的时间
    maxWait: 60000
    # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    validationQuery: select 'x'
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    # 打开PSCache，并且指定每个连接上PSCache的大小
    poolPreparedStatements: true
    maxOpenPreparedStatements: 20
    # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

## 该配置节点为独立的节点，有很多同学容易将这个配置放在spring的节点下，导致配置无法被识别
mybatis:
  # #注意：一定要对应mapper映射xml文件的所在路径
  mapper-locations: classpath:/mappers/*.xml
  # 注意 对应实体的路径
  type-aliases-package: com.makesailing.neo.domain

#  分页插件
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
```

##### 4.最后别忘记了在 启动类上加 @MapperScan("com.makesailing.neo.mappers") 


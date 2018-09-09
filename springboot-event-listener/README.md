### SpringBoot使用ApplicationEvent&Listener完成业务解耦

**ApplicationEvent** 以及 **Listener** 是Spring为我们提供的一个事件监听 ,订阅的实现 ,内部实现原理是观察者设计模式,设计初衷也是为了系统业务逻辑之间的解耦,提高可扩展性以及可维护性 . 事件发布者并不需要考虑谁去监听 , 监听具体的实现内容是什么 , 发布者的工作只是为了发布事件而已 .

>我们平时日常生活中也是经常会有这种情况存在 , 如 : 我们在平时拔河比赛中,裁判员给我们吹响了开始的信息,也就是给我们发布了一个开始的事件,而拔河双方人员都在监听着这个事件,一旦事件发布后双方人员就开始往自己方使劲 .而裁判并不关心你的比赛的过程,只是给你发布事件你执行就可以了

#### 1. 目标

在 **SpringBoot 平台上通过ApplicationEvent 以及 Listener来完成简单的注册事件流程

#### 2. 构建项目

采用 maven 的方式来构建项目 , pom.xml 如下: 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
 <modelVersion>4.0.0</modelVersion>

 <groupId>com.makesailing.neo</groupId>
 <artifactId>springboot-event-listener</artifactId>
 <version>0.0.1-SNAPSHOT</version>
 <packaging>jar</packaging>

 <name>springboot-event-listener</name>
 <description> Spring Boot 事件监听 订阅</description>

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
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <optional>true</optional>
  </dependency>
  <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
  </dependency>
 </dependencies>

 <build>
  <plugins>
   <plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
   </plugin>
  </plugins>
 </build>


</project>
```

#### 具体实现

**创建 UserRegisterEvent**

我们先来创建一个事件,监听都是围绕着事件来关联的 . 事件代码如下

```java
package com.makesailing.neo.event;

import com.makesailing.neo.entity.UserEntity;
import org.springframework.context.ApplicationEvent;

/**
 * # 用户注册监听事件
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:32
 */
public class UserRegisterEvent extends ApplicationEvent {

	// 注册用户对象
	private UserEntity userEntity;

	/**
	 * Create a new ApplicationEvent.
	 * 重写构造方法
	 *
	 * @param source 发生事件的对象
	 */
	public UserRegisterEvent(Object source, UserEntity userEntity) {
		super(source);
		this.userEntity = userEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

}


```

```java
package com.makesailing.neo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:35
 */
@Data
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5899295761293711597L;

	private Long id;

	private String name;

	private String password;
}



```

简单创建一个用户实体,包含 id , name , password 字段 . 自定义事件UserRegisterEvent 继承了ApplicationEvent , 继承后必须重载构造函数,构造函数的参数可以任意指定 ,其中source参数指的是发生事件的对象,一般我们在发布事件时用this关键字代替本类对象,而userEntity参数是自定义的注册用户对象,该对象可以在监听内被获取.

> 在Spring 内部中有多种方式实现监听 : @EventListener注解 、实现 ApplicationListener泛型接口 、实现 SmartApplicationListener接口等 , 我们下面来讲解这三种方式分别实现 .

**创建UserService**

UserSerivce 内添加一个注册方法,该方法只是实现事件发布功能 ,代码如下 : 

```java
package com.makesailing.neo.service;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:39
 */
@Service
public class UserService {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 用户注册
	 * @param userEntity
	 */
	public void register(UserEntity userEntity) {
		// 省略其他逻辑

		// 发布 userRegisterEvent 事件
		UserRegisterEvent event = new UserRegisterEvent(this, userEntity);
		applicationContext.publishEvent(event);
	}
}


```

事件发布是由ApplicationContent对象管控的 ,我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布.

**创建UserController**

创建一个@RestController控制器,对应添加一个注册方法简单实现,代码如下:

```java
package com.makesailing.neo.controller;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 8:59
 */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public String register(@RequestBody UserEntity userEntity) {
    userService.register(userEntity);
    return "注册成功";
  }
}

```

**@EventListener 实现监听**

注解方式比较简单,并不需要实现任何接口,具体代码如下: 

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;

/**
 * # 使用@EventListener 方法实现注册事件监听
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:02
 */
@Component
public class AnnotationRegisterListener {

  @EventListener
  public void register(UserRegisterEvent userRegisterEvent) {
      // 获取用户对象
    UserEntity userEntity = userRegisterEvent.getUserEntity();

    // .. 省略逻辑

    //输出用户信息
    System.out.println("@EventListener 注册信息  用户名 : " + userEntity.getName() + " ,密码 : " + userEntity.getPassword() );
  }
}

```

我们只需要让我们的监听类被Spring所管理就可,在我们用户注册监听实现方法添加@EventListener注解,该注解会根据方法内的配置的事件完成监听 . 下面我们启动项目来调试下我们事件发布时是否被监听者所感知.

**测试事件监听**

使用SpringBootApplication方式启动成功后,我们使用postman来进行测试:

![](https://user-gold-cdn.xitu.io/2018/9/9/165bd343cbd2d93c?w=943&h=193&f=png&s=17475)

![](https://user-gold-cdn.xitu.io/2018/9/9/165bd34b89451996?w=635&h=132&f=png&s=12077)

点击 **Send** 按键,我们可以看到**注册成功**

![](https://user-gold-cdn.xitu.io/2018/9/9/165bd35787103e14?w=1043&h=161&f=png&s=11612)

查看idea 控制台,输出内容为:

```java
2018-09-09 15:30:00.212  INFO 10152 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-09 15:30:00.212  INFO 10152 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-09 15:30:00.252  INFO 10152 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 40 ms
@EventListener 注册信息  用户名 : jamie ,密码 : 12345
```

可以看到我们使用@EventListener注解配置的监听已经生效了,当我们在UserSerivce内发布了注册事件时,监听方法自动被调用并且输出信息到控制台.

**ApplicationListener实现监听**

这种方式也是Spring之前比较常用的监听事件方式,在实现ApplicationListener接口时需要将监听事件作为 泛型传递,监听实现代码如下: 

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 原始方法实现事件监听
 *   用户注册监听
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:48
 */
@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

  /**
   * 实现监听
   * @param event
   */
  @Override
  public void onApplicationEvent(UserRegisterEvent event) {
    UserEntity userEntity = event.getUserEntity();

    // .. 省略逻辑

    System.out.println("实现 ApplicationListener 监听 注册信息  用户名 : " + userEntity.getName() + " , 密码 : " + userEntity.getPassword() );
  }
}

```

我们实现接口后需要使用@Component注解来声明该监听需要被Spring注入管理,当有UserRegisterEvent事件发布时监听程序会自动调用onApplicationEvent方法并且将UserRegisterEvent对象作为参数传递. 

我们UserService内的发布事件不需要修改,我们重启下项目再次访问之前的地址,查看控制台输出内容如下:

```java
2018-09-09 15:39:15.163  INFO 9512 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-09 15:39:15.163  INFO 9512 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-09 15:39:15.194  INFO 9512 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 31 ms
实现 ApplicationListener 监听 注册信息  用户名 : jamie , 密码 : 12345
```

我们看到了控制台打印了我们监听内输出用户信息,事件发布后就不会考虑具体哪个监听去处理业务,甚至可以存在多个监听同时需要处理业务逻辑.

>我们在注册时如果不仅仅是记录注册信息到数据库,还需要发送邮件通知用户.当然我们可以创建多个监听同时监听UserRegisterEvent事件,接下来我们实现这个需求 .

**邮件通知监听**

我们使用注解的方式来完成邮件发送监听实现,代码如下:

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * # 注册用户,发送成功邮件
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 9:59
 */
@Component
public class RegisterUserEmailListener {

  @EventListener
  public void sendEmail(UserRegisterEvent userRegisterEvent) {
    System.out.println("注册成功 , 发送邮件");
  }
}

```

监听编写完成后,我们重启项目,再次访问注册事件请求地址查看控制台输出内容如下所示 : 

```java
2018-09-09 15:44:56.786  INFO 14548 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-09 15:44:56.786  INFO 14548 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-09 15:44:56.816  INFO 14548 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 30 ms
注册成功 , 发送邮件
@EventListener 注册信息  用户名 : jamie ,密码 : 12345

```

我们看到控制台输出的内容感到比较疑惑,我注册时用户信息写入数据库应该在发送邮件前面,为什么没有在第一步执行呢 ? 

好了,证明了一点,事件监听是无序的,监听到的事件先后顺序完成随机出现的.我们接下来使用SmartApplicationListener实现监听方式来实现该逻辑.

**SmartApplicationListener实现有充监听**

我们对注册用户以及邮件的监听重新编写,注册用户写入数据库监听代码如下:

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import com.makesailing.neo.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 用户注册 > 保存用户信息监听
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:02
 */
@Component
public class UserRegisterListener implements SmartApplicationListener {

  /**
   * 该方法返回true&supportSourceType同样返回true时,才会调用该监听内的onApplicationEvent方法
   * @param eventType
   * @return
   */
  @Override
  public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
    // 只有 UserRegisterEvent 监听类型才有执行下面的逻辑
    return eventType == UserRegisterEvent.class;
  }

  /**
   * 该方法返回true&supportEventType同样返回true时,才会调用该监听内的onApplicationEvent方法
   */
  @Override
  public boolean supportsSourceType(Class<?> sourceType) {
    // 只有在 UserService 发布的 UserRegisterEvent 才会执行下面的逻辑
    return sourceType == UserService.class;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    // 转换事件类型
    UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
    // 获取注册用户对象信息
    UserEntity userEntity = userRegisterEvent.getUserEntity();

    System.out.println("实现 SmartApplicationListener 监听 注册信息  用户名 : " + userEntity.getName() + " , 密码 : " + userEntity.getPassword() );
  }

  /**
   * 同步情况下监听执行顺序
   * @return
   */
  @Override
  public int getOrder() {
    return 0;
  }
}

```

>SmartApplicaitonListener接口继承了全局监听ApplicationListener,并且泛型对象使用的ApplicationEvent来作为全局监听,可以理解为使用SmartApplicationListener作为监听父接口的实现,监听所有事件发布.

既然是监听所有的事件发布,那么SmartApplicationListener接口添加了两个方法 supportsEventType 、supportsSourceType来作为区分是否是我们监听的事件,只有这两个方法同时返回true时才会执行onApplicationEvent方法.

可以看到除了上面的方法,还提供了一个getOrder方法,这个方法就可以解决执行监听的顺序问题,return数值越小证明优先级越高,执行顺序越靠前 .

注册成功发送邮件通知监听代码如下:

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import com.makesailing.neo.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 用户注册成功监听,发送邮件
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:13
 */
@Component
public class UserRegisterSendMailListener implements SmartApplicationListener {

  @Override
  public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
    return eventType == UserRegisterEvent.class;
  }

  @Override
  public boolean supportsSourceType(Class<?> sourceType) {
    return sourceType == UserService.class;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;

    UserEntity userEntity = userRegisterEvent.getUserEntity();

    System.out.println("用户 : " + userEntity.getName() + " , 注册成功,发送邮件通知");
  }

  @Override
  public int getOrder() {
    return 1;
  }
}

```

在getOrder方法内我们返回的数值为 "1" ,这就证明了需要在保存注册用户信息监听后执行,下面我们重启项目访问注册地址查看控制台输出的内容如下:

```java
2018-09-09 15:58:11.507  INFO 11416 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-09 15:58:11.507  INFO 11416 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-09 15:58:11.542  INFO 11416 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 35 ms
实现 SmartApplicationListener 监听 注册信息  用户名 : jamie , 密码 : 12345
用户 : jamie , 注册成功,发送邮件通知
```

这次我们看到了输出的顺序就是正确的了,先保存信息然后再发送邮件通知.

>如果说我们不希望在执行监听时等待监听业务逻辑耗时，发布监听后立即要对接口或者界面做出反映，我们该怎么做呢？

**使用@Async实现异步监听**

@Aysnc其实是Spring内的一个组件，可以完成对类内单个或者多个方法实现异步调用，这样可以大大的节省等待耗时。内部实现机制是线程池任务ThreadPoolTaskExecutor，通过线程池来对配置@Async的方法或者类做出执行动作。

**线程任务池配置**

我们创建一个ListenerAsyncConfiguration，并且使用@EnableAsync注解开启支持异步处理，具体代码如下所示：

```java
package com.makesailing.neo.config;

import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * # 异步监听配置
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:26
 */
@Configuration
@EnableAsync
public class ListenerAsyncConfiguration implements AsyncConfigurer {

  @Override
  public Executor getAsyncExecutor() {
    // 使用 spring 内置线程池对象
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    //设置线程池参数
    taskExecutor.setCorePoolSize(5);
    taskExecutor.setMaxPoolSize(10);
    taskExecutor.setQueueCapacity(25);
    taskExecutor.initialize();
    return taskExecutor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return null;
  }
}

```

我们自定义的监听异步配置类实现了AsyncConfigurer接口并且实现内getAsyncExecutor方法以提供线程任务池对象的获取。

我们只需要在异步方法上添加@Async注解就可以实现方法的异步调用，为了证明这一点，我们在发送邮件onApplicationEvent方法内添加线程阻塞10秒，修改后的代码如下:

```java
package com.makesailing.neo.listener;

import com.makesailing.neo.entity.UserEntity;
import com.makesailing.neo.event.UserRegisterEvent;
import com.makesailing.neo.service.UserService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * # 用户注册成功监听,发送邮件
 *
 * @Author: jamie.li
 * @Date: Created in  2018/9/9 11:13
 */
@Component
public class UserRegisterSendMailListener implements SmartApplicationListener {

  @Override
  public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
    return eventType == UserRegisterEvent.class;
  }

  @Override
  public boolean supportsSourceType(Class<?> sourceType) {
    return sourceType == UserService.class;
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    try {
      Thread.sleep(10000);//静静的沉睡3秒钟
    }catch (Exception e){
      e.printStackTrace();
    }
    UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;

    UserEntity userEntity = userRegisterEvent.getUserEntity();

    System.out.println("用户 : " + userEntity.getName() + " , 注册成功,发送邮件通知");
  }

  @Override
  public int getOrder() {
    return 1;
  }
}

```

下面我们重启下项目，访问注册地址，查看界面反映是否也有延迟。
我们测试发现访问界面时反映速度要不之前还要快一些，我们去查看控制台时，可以看到注册信息输出后等待3秒后再才输出邮件发送通知，而在这之前界面已经做出了反映。

>注意：如果存在多个监听同一个事件时，并且存在异步与同步同时存在时则不存在执行顺序。

#### 总结

我们在传统项目中往往各个业务逻辑之间耦合性较强，因为我们在service都是直接引用的关联service或者jpa来作为协作处理逻辑，然而这种方式在后期更新、维护性难度都是大大提高了。然而我们采用事件通知、事件监听形式来处理逻辑时耦合性则是可以降到最小。






























































### SpringBoot使用@Scheduled创建定时任务

定时任务一般会存在中大型企业级项目中，为了减少服务器、数据库的压力往往会采用时间段性的去完成某些业务逻辑。比较常见的就是金融服务系统推送回调，一般支付系统订单在没有收到成功的回调返回内容时会持续性的回调，这种回调一般都是定时任务来完成的。还有就是报表的生成，我们一般会在客户访问量过小的时候来完成这个操作，那往往都是在凌晨。这时我们也可以采用定时任务来完成逻辑。SpringBoot为我们内置了定时任务，我们只需要一个注解就可以开启定时为我们所用了.

#### 1. 构建项目

pom.xml 依赖如下 : 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.makesailing.neo</groupId>
	<artifactId>springboot-scheduled</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>springboot-scheduled</name>
	<description>Demo project for Spring Boot</description>

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

在程序入口 Application.java 添加注解 **@EnableScheduling**

```java
@SpringBootApplication
@EnableScheduling
public class SpringbootScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScheduledApplication.class, args);
	}
}
```

##### 2. 创建 PrintTask 类

**@Scheduled**

@scheduled注解用来配置到方法上来完成对应的定时任务的配置，如执行时间，间隔时间，延迟时间等等，下面我们就来详细的看下对应的属性配置。

```java
@Component
public class PringTask {
    
}
```

**cron属性**  

eg :  每5秒执行一次

```java
/**
	 * 每5秒执行一次
	 */
	@Scheduled(cron = "0/5 * * * * *")
	public void cron() {
		System.out.println("执行测试cron时间" + new Date(System.currentTimeMillis()));
	}
```

输出如下:

```tex
执行测试cron时间Sat Sep 08 16:49:25 CST 2018
执行测试cron时间Sat Sep 08 16:49:30 CST 2018
执行测试cron时间Sat Sep 08 16:49:35 CST 2018
执行测试cron时间Sat Sep 08 16:49:40 CST 2018
执行测试cron时间Sat Sep 08 16:49:45 CST 2018
```

**备注**

这是一个时间表达式，可以通过简单的配置就能完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域：

**Seconds** : 可出现", - * /"四个字符，有效范围为0-59的整数
**Minutes** : 可出现", - * /"四个字符，有效范围为0-59的整数
**Hours** : 可出现", - * /"四个字符，有效范围为0-23的整数
**DayofMonth** : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
**Month** : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
**DayofWeek** : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
**Year** : 可出现", - * /"四个字符，有效范围为1970-2099年

下面简单举几个例子：

"0 0 12 * * ?"    每天中午十二点触发
"0 15 10 ? * *"    每天早上10：15触发
"0 15 10 * * ?"    每天早上10：15触发
"0 15 10 * * ? *"    每天早上10：15触发
"0 15 10 * * ? 2005"    2005年的每天早上10：15触发
"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发

#### fixedRate属性

该属性的含义是上一个调用开始后再次调用的延时（不用等待上一次调用完成），这样就会存在重复执行的问题，所以不是建议使用，但数据量如果不大时在配置的间隔时间内可以执行完也是可以使用的.

```java
     /**
	 * 是上一次调用开始再次调用的延时(不用等待上次的调用完成)
	 * @throws InterruptedException
	 */
	@Scheduled(fixedRate = 1000 * 1)
	public void fixedRate() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("执行测试fixedRate时间" + new Date(System.currentTimeMillis()));
	}
```

输出如下 : 

```tex
执行测试fixedRate时间Sat Sep 08 16:51:42 CST 2018
执行测试fixedRate时间Sat Sep 08 16:51:44 CST 2018
执行测试fixedRate时间Sat Sep 08 16:51:46 CST 2018
执行测试fixedRate时间Sat Sep 08 16:51:48 CST 2018
执行测试fixedRate时间Sat Sep 08 16:51:50 CST 2018
```

**备注 :**

我配置的间隔时间是1秒，我在方法内使用了线程休眠 .启动项目,看到的控制台的内容 ,每一次打印的间隔都是2秒钟，也就是我们配置线程休眠的时间，很好的证实了该方法并没有等到执行完再开始下一次执行。

#### fixedDelay属性

该属性的功效与上面的fixedRate则是相反的，配置了该属性后会等到方法执行完成后延迟配置的时间再次执行该方法。

```java
/**
	 * 是上一次调用完成后再次调用的延时
	 * @throws InterruptedException
	 */
	@Scheduled(fixedDelay = 1000 * 1)
	public void fixedDelay() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("执行测试fixedDelay时间" + new Date(System.currentTimeMillis()));
	}
```

输出如下:

```tex
执行测试fixedDelay时间Sat Sep 08 16:56:55 CST 2018
执行测试fixedDelay时间Sat Sep 08 16:56:59 CST 2018
执行测试fixedDelay时间Sat Sep 08 16:57:03 CST 2018
执行测试fixedDelay时间Sat Sep 08 16:57:07 CST 2018
执行测试fixedDelay时间Sat Sep 08 16:57:11 CST 2018
```

**备注:**

重启项目,查看控制台的内容: 时间间隔是4秒钟，我们在方法内仅仅使线程休眠了3秒钟，配置方法的延迟执行时间则是1秒钟，证明了确实是在方法执行完成后延迟配置时间后再次执行该方法。

#### initialDelay属性

该属性跟上面的fixedDelay、fixedRate有着密切的关系，为什么这么说呢？该属性的作用是第一次执行延迟时间，只是做延迟的设定，并不会控制其他逻辑，所以要配合fixedDelay或者fixedRate来使用. 	

```java
/**
	 * 第一次执行延迟时间
	 * @throws InterruptedException
	 */
	@Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000* 2)
	public void initialDelay() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("执行测试initialDelay时间" + new Date(System.currentTimeMillis()));
	}
```

输出如下:

```tex
2018-09-08 16:59:41.136  INFO 23608 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8082 (http)
2018-09-08 16:59:41.139  INFO 23608 --- [           main] c.m.neo.SpringbootScheduledApplication   : Started SpringbootScheduledApplication in 1.901 seconds (JVM running for 2.494)
执行测试initialDelay时间Sat Sep 08 16:59:54 CST 2018
执行测试initialDelay时间Sat Sep 08 16:59:59 CST 2018
执行测试initialDelay时间Sat Sep 08 17:00:04 CST 2018
执行测试initialDelay时间Sat Sep 08 17:00:09 CST 2018
```

**备注 :**

重启项目，查看控制台输出，这次我们等待了10秒钟后才看到了第一次输出内容


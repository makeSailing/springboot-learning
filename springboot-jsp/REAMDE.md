#### 创建SpringBoot jsp工程

##### 1. ![image](https://user-images.githubusercontent.com/42613962/44527612-f1f22780-a719-11e8-8fce-4058d96e1357.png)

![image](https://user-images.githubusercontent.com/42613962/44527652-1221e680-a71a-11e8-89c5-e6f0c055be3c.png)

#####  添加所依赖的jar

```java
<!--jsp start-->
<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
</dependency>
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>jstl</artifactId>
</dependency>
<!--jsp end-->
```
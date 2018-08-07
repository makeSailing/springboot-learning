#### SpringBoot Swagger2 初步使用

- **1. 引入 Swagger2 相关的 jar **

  ```powershel
  		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
  		<dependency>
  			<groupId>io.springfox</groupId>
  			<artifactId>springfox-swagger2</artifactId>
  			<version>2.8.0</version>
  		</dependency>

  		<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
  		<dependency>
  			<groupId>io.springfox</groupId>
  			<artifactId>springfox-swagger-ui</artifactId>
  			<version>2.8.0</version>
  		</dependency>
  ```

- **2. 在 项目入口函数 xxxApplication 相同路径下,新建 Swagger2 类**

  ```java
  @Configuration
  @EnableSwagger2
  public class Swagger2 {

  	@Bean
  	public Docket createRestApi() {
  		return new Docket(DocumentationType.SWAGGER_2)
  			.apiInfo(apiInfo())
  			.select()
  			// 为当前包的路径
  			.apis(RequestHandlerSelectors.basePackage("com.makesailing.neo.controller"))
  			.paths(PathSelectors.any())
  			.build();
  	}

  	private ApiInfo apiInfo() {
  		return new ApiInfoBuilder()
  			// 页面标题
  			.title("Spring Boot中使用Swagger2构建RESTful API")
  			// 描述
  			.description("Spring Boot中使用Swagger2构建RESTful API")
  			.termsOfServiceUrl("http://www.baidu.com/")
  			// 版本号
  			.version("1.0")
  			.build();
  	}
  }
  ```

- **3. 在 相关的 controller 类中加入相应的 swagger 注解说明**

  ```java
  @RequestMapping("/user")
  @RestController
  public class UserController {

  	/**
  	 * 创建线程安全的 map
  	 */
  	static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<>());

  	@ApiOperation(value = "获取用户列表",notes = "获取用户列表")
  	@GetMapping("/users")
  	public List<User> getUserList() {
  		List<User> list = new ArrayList<>(userMap.values());
  		return list;
  	}

  	@ApiOperation(value = "新增用户",notes = "新增用户")
  	@PostMapping("/add")
  	public String addUser(@RequestBody User user) {
  		userMap.put(user.getId(), user);
  		Map<String, String> map = new HashMap<>();
  		map.put("status", "200");
  		map.put("data", JSON.toJSONString(user));
  		return JSON.toJSONString(map);
  	}

  	@ApiOperation(value = "根据用户ID获取用户信息",notes = "根据用户ID获取用户信息")
  	@GetMapping("/{id}")
  	public User getUserById(@PathVariable("id") Long id) {
  		return userMap.get(id);
  	}

  	@ApiOperation(value = "根据用户ID修改用户信息",notes = "根据用户ID修改用户信息")
  	@PutMapping("/update")
  	public String updateUser(@RequestBody User user) {
  		User u = userMap.get(user.getId());
  		u.setName(user.getName());
  		u.setAge(user.getAge());
  		return "success";
  	}

  	@ApiOperation(value = "根据用户ID删除用户",notes = "根据用户ID删除用户")
  	@DeleteMapping("/{id}")
  	public String deleteUserById(@PathVariable("id")Long id){
  		userMap.remove(id);
  		return "success";
  	}
  }
  ```

- **4. 在 浏览器上输入 127.0.0.1:8080/swagger-ui.html 就能看到页面**

  ![1533636601289](https://goss.veer.com/creative/vcg/veer/800water/veer-152995888.jpg)


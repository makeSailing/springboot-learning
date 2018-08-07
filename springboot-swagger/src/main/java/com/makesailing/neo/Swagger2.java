package com.makesailing.neo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * #
 *
 *
 * @date 2018/8/7 16:32
 */
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



package com.nagra.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.oas.annotations.EnableOpenApi;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@SpringBootApplication
@EnableOpenApi
//@ComponentScan(basePackages = "com.nagra.microservice")
@EnableJpaRepositories
public class MicroserviceApplication {
=======
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class MicroserviceApplication {

>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}
}

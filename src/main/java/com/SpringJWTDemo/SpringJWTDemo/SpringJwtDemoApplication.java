package com.SpringJWTDemo.SpringJWTDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.SpringJWTDemo.SpringJWTDemo.*")
public class SpringJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtDemoApplication.class, args);
	}

}

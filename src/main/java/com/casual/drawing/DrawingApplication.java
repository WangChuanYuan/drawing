package com.casual.drawing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 王川源
 * Spring Boot启动类
 */
@SpringBootApplication
public class DrawingApplication extends SpringBootServletInitializer{

	static {
		System.load("C:/opencv/build/java/x64/opencv_java343.dll");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
		return springApplicationBuilder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(DrawingApplication.class, args);
	}
}

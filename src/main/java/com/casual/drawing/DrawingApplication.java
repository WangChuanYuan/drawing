package com.casual.drawing;

import org.opencv.core.Core;
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
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
		return springApplicationBuilder.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(DrawingApplication.class, args);
	}
}

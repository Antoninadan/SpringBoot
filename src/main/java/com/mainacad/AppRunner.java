package com.mainacad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication // Spring Boot включит автоматическую настройку и отсканирует ваши ресурсы в текущем пакете
@EnableSwagger2
//@EnableScheduling
public class AppRunner {

	public static void main(String[] args) {

//		SpringApplication.run(AppRunner.class, args);

		SpringApplication context = new SpringApplication(com.mainacad.AppRunner.class);
		context.setAdditionalProfiles("rest");
		context.run(args);
	}
}

package com.eugene.geimagetester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeimagetesterApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(GeimagetesterApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}

package it.jac.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ProjectWorkBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectWorkBackApplication.class, args);
	}
}

package com.teste.backendjavamagic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
public class BackendJavaMagicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJavaMagicApplication.class, args);
	}
	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}
}

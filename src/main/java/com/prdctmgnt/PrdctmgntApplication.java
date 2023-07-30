package com.prdctmgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.prdctmgnt.*")
public class PrdctmgntApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrdctmgntApplication.class, args);
	}

}

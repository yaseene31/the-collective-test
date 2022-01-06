package com.the.collective.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.the.collective.test"})
public class TheCollectiveTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCollectiveTestApplication.class, args);
	}
}
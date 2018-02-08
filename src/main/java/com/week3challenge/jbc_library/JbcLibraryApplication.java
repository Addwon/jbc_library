package com.week3challenge.jbc_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class JbcLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JbcLibraryApplication.class, args);
	}
}

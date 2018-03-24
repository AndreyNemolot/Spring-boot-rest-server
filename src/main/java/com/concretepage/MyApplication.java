package com.concretepage;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan
@EntityScan(basePackages = "com.concretepage.entity")
public class MyApplication {  
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
    }
}

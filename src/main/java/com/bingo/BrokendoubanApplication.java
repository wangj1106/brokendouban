package com.bingo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bingo.repository")
public class BrokendoubanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrokendoubanApplication.class, args);
	}
}

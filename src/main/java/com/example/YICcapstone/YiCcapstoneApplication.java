package com.example.YICcapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class YiCcapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiCcapstoneApplication.class, args);
	}

}

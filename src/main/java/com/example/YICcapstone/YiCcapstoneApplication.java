package com.example.YICcapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing // 우준 : 엔티티가 생성시각, 수정시각, 생성자, 수정자를 기록해주는 Auditing 기능 제공
public class YiCcapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiCcapstoneApplication.class, args);
	}

}

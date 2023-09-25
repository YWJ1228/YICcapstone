package com.example.YICcapstone.domain.member.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties") // 이메일 인증 전송을 위한 설정값 위치
public class EmailConfig {

    @Value("${spring.mail.username}") // 이메일 전송 계정 아이디
    private String id;
    @Value("${spring.mail.password}") // 이메일 전송 계정 패스워드
    private String password;
    @Value("${spring.mail.host}") // 이메일 전송 도메인 및 smtp 설정
    private String host;
    @Value("${spring.mail.port}") // 이메일 전송 도메인 포트번호
    private int port;

    @Bean
    public JavaMailSender javaMailService() { // 이메일 생성
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host); // 사용할 smtp 서버 주소 적용
        javaMailSender.setUsername(id); // 발신 메일 아이디 적용
        javaMailSender.setPassword(password); // 발신 메일 패스워드 적용
        javaMailSender.setPort(port); //smtp port 적용
        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp starttls 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버 주소
        properties.setProperty("mail.smtp.ssl.enable","true"); // ssl 사용
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }
}

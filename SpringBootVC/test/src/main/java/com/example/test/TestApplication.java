package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트 application의 시작점이 되는 Class
@SpringBootApplication		//스프링 부트의 주요설정 클래스 지정 - 설정 초기화
public class TestApplication {


	//main 메소드 : 프로그램 시작
	//내장된 웹 서버(Tomcat), 웹 WEB application을 초기화하고 구동(실행)
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}

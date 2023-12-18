package com.example.test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//서블릿을 초기화하는 클래스입니다.
// - war파일로 프로젝트를 패키징합니다.
// - 서블릿 컨테이너의 배포할 설정들을 초기화합니다.
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestApplication.class);		//스프링 부트 구동 클래스를 지정
	}

}


package com.joeun.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {
 
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();   // BCRYPT 해시 알고리즘을 사용하여 비밀번호를 저장하는 구현클래스입니다.
    //NoOpPasswordEncoder 암호화 없이 비밀번호를 저장하는 구현클래스입니다.
    //...
  };


}

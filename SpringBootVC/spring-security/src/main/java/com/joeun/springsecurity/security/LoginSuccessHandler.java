package com.joeun.springsecurity.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import lombok.extern.slf4j.Slf4j;


//로그인 성공 처리 클래스
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {
        log.info("로그인 인증 성공...");

        //아이디 저장
        String rememberId = request.getParameter("rememberId"); //아이디 저장 여부
        String username = request.getParameter("username");
        log.info("rememberId : "+rememberId);
        log.info("username : "+username);

        //아이디 저장 체크
        if(rememberId!=null&&rememberId.equals("on")){
          Cookie cookie = new Cookie("rememberId", username); //쿠키에 아이디 등록
          cookie.setMaxAge(60*60*24*7); //유효기간 7일
          cookie.setPath("/");        //쿠키를 전체 구간에서 읽을 수 있게끔
          response.addCookie(cookie);
        }
        else{
          Cookie cookie = new Cookie("rememberId", username); //쿠키에 아이디 등록
          cookie.setMaxAge(0); //유효기간 7일
          cookie.setPath("/");        //쿠키를 전체 구간에서 읽을 수 있게끔
          response.addCookie(cookie);

        }

        User user = (User) authentication.getPrincipal();    //인증된 사용자 정보를 가져옵니다.
        //이녀석은 노출되지 않습니다.

        log.info("아이디 : "+user.getUsername());
        log.info("아이디 : "+user.getPassword());
        log.info("아이디 : "+user.getAuthorities());

        super.onAuthenticationSuccess(request, response, authentication);

  }
  
}

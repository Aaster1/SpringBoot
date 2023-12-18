package com.joeun.springsecurity.service;

import javax.servlet.http.HttpServletRequest;


import com.joeun.springsecurity.dto.Users;

public interface UserService {
  
    //회원 등록
  public int insert(Users users) throws Exception;
  
  //회원 조회
  public Users select(int userNo) throws Exception;

  //회운가입 시 바로 로그인
  public void login(Users users, HttpServletRequest request) throws Exception;

  //사용자 정의 인증
  public Users login(String username) throws Exception;

}

package com.joeun.springsecurity.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
  
  private int userNo;
  private String userId;
  private String userPw;
  private String userPwCheck;   //비밀번호 확인
  private String name;
  private String email;
  private Date regDate;
  private Date updDate;
  private int enabled;  //휴면 여부


  //권한목록
  List<UserAuth> authList;

}
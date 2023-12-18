package com.joeun.springsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.springsecurity.dto.UserAuth;
import com.joeun.springsecurity.dto.Users;

@Mapper
public interface UserMapper {

  //회원 등록
  public int insert(Users users) throws Exception;
  
  //회원 조회
  public Users select(int userNo) throws Exception;

  //회원 권한 등록
  public int insertAuth(UserAuth userAuth) throws Exception;

  //사용자정의 인증
  public Users login(String username) throws Exception;

}

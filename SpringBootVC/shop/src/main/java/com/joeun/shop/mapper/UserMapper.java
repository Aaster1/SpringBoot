package com.joeun.shop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.shop.dto.UserAuth;
import com.joeun.shop.dto.Users;

@Mapper
public interface UserMapper {

  //회원 등록
  public int insert(Users users) throws Exception;
  
  //회원 조회
  public Users select(int userNo) throws Exception;
  //아이디로 회원 정보 조회
  public Users selectById(String username) throws Exception;

  //회원 권한 등록
  public int insertAuth(UserAuth userAuth) throws Exception;

  //사용자정의 인증
  public Users login(String username) throws Exception;

  //사용자 정보 수정
  public int update(Users users) throws Exception;



}

package com.joeun.springsecurity.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.joeun.springsecurity.dto.UserAuth;
import com.joeun.springsecurity.dto.Users;
import com.joeun.springsecurity.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{


  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserMapper userMapper;

  @Override
  public int insert(Users users) throws Exception {
    users.setUserPw(passwordEncoder.encode( users.getUserPw()));
    //회원등록
    int result = userMapper.insert(users);


    if(result>0){
    //권한등록
    UserAuth userAuth = new UserAuth();
    userAuth.setUserId(users.getUserId());
    userAuth.setAuth("ROLE_USER");
    result +=userMapper.insertAuth(userAuth);
}


    return result;

  }

  @Override
  public Users select(int userNo) throws Exception {
    Users users = userMapper.select(userNo);

    return users;
  }

  @Override
  public void login(Users user, HttpServletRequest requset) throws Exception {

      String username = user.getUserId();

      //이미 로그인 과정에서 암호화된 단계입니다. 그래서 getUserPw()를 가져오면 암호화 되어있기에 문제가 생깁니다.
      //따라서 아직 암호화가 이루어지지 않은 확인용 UserPwCheck 를 가져와 토큰으로 등록합니다.;
      String password = user.getUserPwCheck();
      log.info("username : " + username);
      log.info("password : " + password);



      // 아이디, 패스워드 인증 토큰 생성
      UsernamePasswordAuthenticationToken token 
          = new UsernamePasswordAuthenticationToken(username, password);

      // 토큰에 요청정보를 등록
      token.setDetails( new WebAuthenticationDetails(requset) );

      // 토큰을 이용하여 인증(로그인)
      Authentication authentication = authenticationManager.authenticate(token);

      User authUser = (User) authentication.getPrincipal();
      log.info("인증된 사용자 아이디 : " + authUser.getUsername());

      SecurityContextHolder.getContext().setAuthentication(authentication);

  }

  @Override
  public Users login(String username) throws Exception{

    return userMapper.login(username);
  }
  
}

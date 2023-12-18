package com.joeun.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.joeun.shop.dto.CustomUser;
import com.joeun.shop.dto.Users;
import com.joeun.shop.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
  
  @Autowired
  private UserMapper userMapper;


    /**
     *  사용자 정의 사용자 인증 메소드
     *  UserDetails
     *    ➡ Users
     *        ⬆ CustomUser   
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("userId : " + username);

        Users users = new Users();

        try {
            users = userMapper.login(username);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        log.info("users : " + users);
        // admin / 123456 / [ROLE_USER, ROLE_ADMIN]

        CustomUser customUser = null;

        
        if( users != null ) {
        customUser = new CustomUser(users);
        log.info(customUser.getUsername());
    }
        return customUser;

    }
  
}

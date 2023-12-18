package com.joeun.springsecurity.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.springsecurity.dto.Users;
import com.joeun.springsecurity.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
public class HomeController {


  @Autowired
  private UserService userService;



  @GetMapping(value={"/",""})
  public String home(Model model, Principal principal) {
    String loginId = principal !=null?principal.getName() : "guest";


    log.info(loginId);

    model.addAttribute("loginId",loginId);
      return "index";
  }
  


/*
 * 로그인 화면
 */

  @GetMapping(value="/login")
  public String login(@CookieValue(value="rememberId",required = false) Cookie cookie, Model model) {
    model.addAttribute("userId", cookie!=null?cookie.getValue():"");
    model.addAttribute("rememberId", cookie!=null?true:false);

    return "login";
  }
  
  /*
   * 
   * 회원 가입 화면
   */

   @GetMapping(value="/join")
   public String join() {
       return "join";
   }

   @PostMapping(value="/join")
   public String joinPor(Users users,HttpServletRequest request)throws Exception{

    

    int result = userService.insert(users);

    if(result>0)    {

      userService.login(users,request);

    }else{

    }

    return "redirect:/";
   }


   @GetMapping(value="/exception")
   public String error(Authentication auth,Model model) {

    log.info(auth.toString());

    model.addAttribute("msg", "인증 거부 : " + auth.toString());

       return "exception";
   }
   
  
}

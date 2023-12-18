package com.joeun.shop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value={"","/"})
  public String index() {
      return "admin/index";
  }
  
  
}

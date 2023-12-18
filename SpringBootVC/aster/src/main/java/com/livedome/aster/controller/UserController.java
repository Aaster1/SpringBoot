package com.livedome.aster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.livedome.aster.dto.TeamApp;
import com.livedome.aster.service.TeamAppService;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private TeamAppService teamAppService;

  

  @GetMapping(value="/listByLider")
  public String listByLider(Model model, TeamApp teamApp) {

    model.addAttribute("resTeamAppList", teamAppService.listByLider(teamApp));

      return "user/appListByLider";
      
  }


  @GetMapping(value="/listByMember")
  public String listByMember(Model model, TeamApp teamApp) {

    model.addAttribute("resTeamAppList", teamAppService.listByMember(teamApp));

      return "user/appListByMember";

  }
  

  
}

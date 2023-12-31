package com.livedome.aster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.livedome.aster.dto.Team;
import com.livedome.aster.dto.TeamApp;
import com.livedome.aster.mapper.TeamMapper;
import com.livedome.aster.service.TeamAppService;
import com.livedome.aster.service.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class TeamController {


  @Autowired
  private TeamService teamService;

  @Autowired
  private TeamAppService teamAppService;

  @Autowired
  private TeamMapper teamMapper;



  @GetMapping(value={"/",""})
  public String index(Model model) {

    model.addAttribute("teamList", teamService.list());

      return "team/list";
  }



  @GetMapping(value="/insert")
  public String insert() {
      return "team/insert";
      
  }
  
  

  @PostMapping(value="/insert")
  public String insertPro(Team team) {
    
    int result = teamService.insert(team);

    if(result>0){
      return "team/insert";
    }

      return "team/list";
  }

  @GetMapping(value="/read")
  public String read(Model model, Team team) {


      
      model.addAttribute("team", teamService.read(team));
      model.addAttribute("teamList", teamService.list());

      return "team/read";
  }



  @GetMapping(value="/update")
  public String update(Model model, Team team) {

      model.addAttribute("team", teamMapper.read(team));

      return "team/update";

  }

  @PostMapping(value="/update")
  public String updatePro(Team team) {

      int result = teamService.update(team);

      if(result>0){

        return "team/list";
      }
      else{
        return "team/update";
      }

      
      
  }
  
  
  @PostMapping(value="delete")
  public String deletePro(Team team) {

      
      int result = teamService.delete(team);
      if(result>0){

        return "team/list";
      }else{
        return "team/list";

      }
  }

  @GetMapping(value="/app")
  public String application(Model model,Team team) {

    model.addAttribute("team",team);

      return "team/teamApp";
  }

  @PostMapping(value="/app")
  public String applicationPro(TeamApp teamApp) {

    
      
      return "user/mypage/reqApp";
  }

  @PostMapping(value="/app/accept")
  public void acceptPro(TeamApp teamApp) {
   
      int result = teamAppService.accept(teamApp);

      //페이지 갱신이 필요합니다.
      //갱신된 리스트를 다시 조회하여 반환해야합니다.
      //추후 비동기 방식으로 수정 예정입니다.

  }


  @PostMapping(value="/app/denied")
  public void deniedPro(TeamApp teamApp) {
   
      int result = teamAppService.denied(teamApp);

      //페이지 갱신이 필요합니다.
      //갱신된 리스트를 다시 조회하여 반환해야합니다.
      //추후 비동기 방식으로 수정 예정입니다.

  }

  @PostMapping(value="/app/confirmed")
  public void confirmedPro(TeamApp teamApp) {
   
      int result = teamAppService.confirmed(teamApp);

      //페이지 갱신이 필요합니다.
      //갱신된 리스트를 다시 조회하여 반환해야합니다.
      //추후 비동기 방식으로 수정 예정입니다.

  }
  
  
  
  
  
  
  
}

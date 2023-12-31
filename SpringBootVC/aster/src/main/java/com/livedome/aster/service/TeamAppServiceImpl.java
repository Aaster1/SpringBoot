package com.livedome.aster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livedome.aster.dto.Team;
import com.livedome.aster.dto.TeamApp;
import com.livedome.aster.mapper.TeamAppMapper;
import com.livedome.aster.mapper.TeamMapper;

@Service
public class TeamAppServiceImpl implements TeamAppService{

  

  @Autowired
  private TeamAppMapper teamAppMapper;

  @Autowired
  private TeamMapper teamMapper;


  @Override
  public int insert(TeamApp teamApp) {

    int result = teamAppMapper.insert(teamApp);

    return result;
  }

  @Override
  public List<TeamApp> listByLider(TeamApp teamApp) {

    List<TeamApp> listByLider = teamAppMapper.listByLider(teamApp);

    return listByLider;

  }

  @Override
  public List<TeamApp> listByMember(TeamApp teamApp) {

     List<TeamApp> listByMember = teamAppMapper.listByMember(teamApp);


    return listByMember;

  }

  @Override
  public TeamApp read(TeamApp teamApp) {
     TeamApp readResult = teamAppMapper.read(teamApp);


    return readResult;
  }

  @Override
  public int accept(TeamApp teamApp) {

     int result = teamAppMapper.accept(teamApp);

    return result;
  }

  @Override
  public int denied(TeamApp teamApp) {

    int result = teamAppMapper.denied(teamApp);

    int deniedAllResult = 0;

    Team tempTeam = new Team();
    tempTeam.setTeamNo(teamApp.getTeamNo());

    Team team = teamMapper.read(tempTeam);

    if(team.getRecStatus()==team.getCapacity()){

      deniedAllResult = teamAppMapper.deniedAll(teamApp);

    }

    return result+deniedAllResult;
  }



  @Override
  public int confirmed(TeamApp teamApp) {

     int result = teamAppMapper.confirmed(teamApp);

    int deniedAllResult = 0;

    Team tempTeam = new Team();
    tempTeam.setTeamNo(teamApp.getTeamNo());

    Team team = teamMapper.read(tempTeam);

    if(team.getRecStatus()==team.getCapacity()){

      deniedAllResult = teamAppMapper.deniedAll(teamApp);

    }

    return result+deniedAllResult;
    
  }
  
}

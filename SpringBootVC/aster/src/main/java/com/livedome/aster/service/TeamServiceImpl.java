package com.livedome.aster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livedome.aster.dto.Team;
import com.livedome.aster.mapper.TeamMapper;


@Service
public class TeamServiceImpl implements TeamService{

  @Autowired
  private TeamMapper teamMapper;

  @Override
  public List<Team> list() {

    

    return teamMapper.list();

  }

  @Override
  public int insert(Team team) {

    int result = teamMapper.insert(team);

    return result;
  }

  @Override
  public int update(Team team) {

    int result = teamMapper.update(team);


    return result;
  }
  
  @Override
  public int delete(Team team) {
    int result = teamMapper.delete(team);
  
  
    return result;
  }
  
  @Override
  public Team read(Team team) {

    int result = teamMapper.addView(team);
    Team resultTeam = null;

    if(result>0){

      resultTeam = teamMapper.read(team);
      return resultTeam;
    }
    else{

      return resultTeam;

    }

  
  
  }


  
}

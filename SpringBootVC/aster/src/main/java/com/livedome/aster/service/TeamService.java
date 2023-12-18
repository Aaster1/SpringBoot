package com.livedome.aster.service;

import java.util.List;


import com.livedome.aster.dto.Team;


public interface TeamService {
  
  public List<Team> list();

  public int insert(Team team);

  public int update(Team team);

  public int delete(Team team);

  public Team read(Team team);  
}

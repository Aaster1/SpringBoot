package com.livedome.aster.service;

import java.util.List;


import com.livedome.aster.dto.TeamApp;


public interface TeamAppService {
  
  public int insert(TeamApp teamApp);
  
  public List<TeamApp> listByLider(TeamApp teamApp);

  public List<TeamApp> listByMember(TeamApp teamApp);

  public TeamApp read(TeamApp teamApp);

  public int accept(TeamApp teamApp);

  public int denied(TeamApp teamApp);

  public int confirmed(TeamApp teamApp);

}

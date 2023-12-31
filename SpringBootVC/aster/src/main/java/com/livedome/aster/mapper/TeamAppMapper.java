package com.livedome.aster.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.livedome.aster.dto.TeamApp;

@Mapper
public interface TeamAppMapper {

  
  public int insert(TeamApp teamApp);
  
  public List<TeamApp> listByLider(TeamApp teamApp);

  public List<TeamApp> listByMember(TeamApp teamApp);

  public TeamApp read(TeamApp teamApp);

  public int accept(TeamApp teamApp);

  public int denied(TeamApp teamApp);

  public int deniedAll(TeamApp teamApp);

  public int confirmed(TeamApp teamApp);

}

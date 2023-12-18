package com.livedome.aster.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.livedome.aster.dto.Team;

@Mapper
public interface TeamMapper {

  public List<Team> list();

  public int insert(Team team);

  public int update(Team team);

  public int delete(Team team);

  public Team read(Team team);  

  public int addView(Team team);
}

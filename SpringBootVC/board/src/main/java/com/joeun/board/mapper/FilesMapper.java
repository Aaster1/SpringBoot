package com.joeun.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.board.dto.Files;

@Mapper
public interface FilesMapper {
  
  //게시글 목록
  public List<Files> list() throws Exception;

  //게시글 목록 - 부모 테이블 기준
  public List<Files> listByParent(Files files) throws Exception;

  //게시글 조회
  public Files read(int fileNo) throws Exception;
  
  //게시글 등록
  public int insert(Files files) throws Exception;
  
  //게시글 수정
  public int update(Files files) throws Exception;
  
  //게시글 삭제
  public int delete(int fileNo) throws Exception;

  //게시글 삭제 - 부모 테이블 기준
  public int deleteByParent(Files files) throws Exception;
  
}

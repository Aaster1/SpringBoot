package com.joeun.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Option;

@Mapper
public interface BoardMapper {
  
  //게시글 목록
  public List<Board> list() throws Exception;

  //게시글 검색 목록
  public List<Board> search(Option option) throws Exception;

  //게시글 조회
  public Board read(int boardNo) throws Exception;
  
  //게시글 등록
  public int insert(Board board) throws Exception;
  
  //게시글 수정
  public int update(Board board) throws Exception;
  
  //게시글 삭제
  public int delete(int boardNo) throws Exception;

  //최근 게시글의 boardNo
  public int maxPk() throws Exception;
  
}

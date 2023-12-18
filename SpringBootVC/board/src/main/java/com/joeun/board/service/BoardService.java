package com.joeun.board.service;

import java.util.List;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Option;

public interface BoardService {

   
  //게시글 목록
  public List<Board> list() throws Exception;

  //게시글 검색 목록
  public List<Board> list(Option option) throws Exception;

  //게시글 조회
  public Board read(int boardNo) throws Exception;
  
  //게시글 등록
  public int insert(Board board) throws Exception;
  
  //게시글 수정
  public int update(Board board) throws Exception;
  
  //게시글 삭제
  public int delete(int boardNo) throws Exception;

}

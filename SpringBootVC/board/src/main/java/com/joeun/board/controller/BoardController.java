package com.joeun.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Files;
import com.joeun.board.dto.Option;
import com.joeun.board.mapper.FilesMapper;
import com.joeun.board.service.BoardService;
import com.joeun.board.service.FilesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@Controller
@Slf4j
@RequestMapping(value="/board")
public class BoardController {
  
  @Autowired
  private BoardService boardService;

  @Autowired
  private FilesService filesService;

  /*
   * 게시글 목록
   * [GET]
   * model : boardList
   */

  @GetMapping(value="/list")
  public String list(Model model) throws Exception{
    model.addAttribute("boardList", boardService.list());



      return "board/list";
  }

  @PostMapping(value="/list")
  public String searchList(Model model, Option option) throws Exception {
      
    model.addAttribute("boardList", boardService.list(option));

      return "board/list";
  }

  @GetMapping(value="/insert")
  public String insert(Model model) {

      return "board/insert";
  }

  @PostMapping(value="/insert")
  public String insertPro(Model model, Board board) throws Exception {

      model.addAttribute("result", boardService.insert(board));
      
      return "redirect:/board/list";
  }

  @GetMapping(value="/read")
  public String read(Model model, @RequestParam("boardNo") int boardNo, Files files) throws Exception {
  
   log.info("[GET] - /board/read");

        // 데이터 요청
        Board board = boardService.read(boardNo);     // 게시글 정보

        files.setParentTable("board");
        files.setParentNo(boardNo);
        List<Files> fileList = filesService.listByParent(files); // 파일 정보

        // 모델 등록
        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
        // 뷰 페이지 지정
      return "board/read";
  }

  @GetMapping(value="/update")
  public String update(Model model,@RequestParam("boardNo") int boardNo) throws Exception {


    model.addAttribute("board", boardService.read(boardNo));
      
      return "board/update";
  }

  @PostMapping(value="/update")
  public String updatePro(Model model, Board board) throws Exception {
      
      model.addAttribute("result", boardService.update(board));

      return "redirect:/board/list";
  }

  @PostMapping(value="/delete")
  public String deletePro(Model model, @RequestParam("boardNo") int boardNo) throws Exception {

    model.addAttribute("result", boardService.delete(boardNo));
      
      return "redirect:/board/list";
  }
  
  
  
  
  

  

}

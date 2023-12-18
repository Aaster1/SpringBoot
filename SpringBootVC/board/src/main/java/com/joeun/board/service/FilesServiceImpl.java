package com.joeun.board.service;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.joeun.board.dto.Files;
import com.joeun.board.mapper.FilesMapper;

@Service
public class FilesServiceImpl implements FilesService{

  @Autowired
  private FilesMapper filesMapper;

  @Override
  public List<Files> list() throws Exception {

    return filesMapper.list();
  }

  @Override
  public Files select(int boardNo) throws Exception {

    return filesMapper.read(boardNo);
  }

  @Override
  public int insert(Files board) throws Exception {

    return filesMapper.insert(board);
  }

  @Override
  public int update(Files board) throws Exception {

    return filesMapper.update(board);
  }

  @Override
  public int delete(int boardNo) throws Exception {

    return filesMapper.delete(boardNo);
  }

  @Override
  public List<Files> listByParent(Files file) throws Exception {

    return filesMapper.listByParent(file);
  }

  @Override
  public int deleteByParent(Files file) throws Exception {
    return filesMapper.deleteByParent(file);
  }

  @Override
  public int download(int fileNo, HttpServletResponse response) throws Exception{


        Files file = filesMapper.read(fileNo);
        if( file == null ) {
          // BAD_REQUEST : 400, 클라이언트의 요청이 잘못되었음을 알려주는 상태코드
          response.setStatus(response.SC_BAD_REQUEST);
          return 0;
      }

        String filePath = file.getFilePath();
        String fileName = file.getFileName();

        // 다운로드 응답을 위한 헤더 세팅
        // - ContentType            : application/octet-stream
        // - Content-Disposition    : attachment, filename="파일명.확장자"
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // 파일 다운로드
        // - 파일 입력
        File downloadFile = new File(filePath);
        FileInputStream fis = new FileInputStream(downloadFile);

        // - 파일 출력
        ServletOutputStream sos = response.getOutputStream();

        // 다운로드
        FileCopyUtils.copy(fis, sos);

          fis.close();
          sos.close();

    return 1;

  }
  

  
}

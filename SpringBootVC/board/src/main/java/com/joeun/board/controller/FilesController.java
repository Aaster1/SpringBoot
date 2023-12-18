package com.joeun.board.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.board.dto.Files;
import com.joeun.board.service.FilesService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private FilesService fileService;

    @GetMapping(value="/{fileNo}")
    public void fileDownload( @PathVariable("fileNo") int fileNo
                             ,HttpServletResponse response  ) throws Exception {
        Files file = fileService.select(fileNo);

       

        int result = fileService.download(fileNo,response);

       

        // byte[] buffer = new byte[1024];             // 1024bytes = 1KB 단위 버퍼
        // int data;
        // while( (data = fis.read(buffer)) != -1 ) {  // 1KB 씩 파일입력
        //     sos.write(buffer, 0, data);         // 1KB 씩 파일출력
        // }
        // fis.close();
        // sos.close();
    }
    
    @DeleteMapping("")
    public ResponseEntity<String> deleteFile(Files files) throws Exception{

        fileService.delete(files.getFileNo());

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

    } 

}

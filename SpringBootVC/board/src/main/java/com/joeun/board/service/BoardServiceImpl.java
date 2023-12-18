package com.joeun.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Files;
import com.joeun.board.dto.Option;
import com.joeun.board.mapper.BoardMapper;
import com.joeun.board.mapper.FilesMapper;

@Service
public class BoardServiceImpl implements BoardService{

  @Autowired
  private BoardMapper boardMapper;

  @Value("${upload.path}")
  private String uploadPath;

  @Autowired
  private FilesMapper filesMapper;

  @Override
  public List<Board> list() throws Exception {

    return boardMapper.list();

  }

  @Override
  public List<Board> list(Option option) throws Exception {

    return boardMapper.search(option);
  }

  @Override
  public Board read(int boardNo) throws Exception {

    return boardMapper.read(boardNo);
  }

  @Override
  public int insert(Board board) throws Exception {
     int result = boardMapper.insert(board);
     String parentTable = "board";
     int parentNo = boardMapper.maxPk();
    

        // 파일 업로드 
        List<MultipartFile> fileList = board.getFiles();

        if( !fileList.isEmpty() )
        for (MultipartFile file : fileList) {
            // 파일 정보 : 원본파일명, 파일 용량, 파일 데이터 
            String originName = file.getOriginalFilename();
            long fileSize = file.getSize();
            byte[] fileData = file.getBytes();
            
            // 업로드 경로
            // 파일명 중복 방지 방법(정책)
            // - 날짜_파일명.확장자
            // - UID_파일명.확장자
            String fileName = UUID.randomUUID().toString() + "_" + originName;
            String filePath = uploadPath + fileName;
            
            //파일업로드에는 두 가지 작업이 필요합니다.
            // - 서버 측에 파일 주가해줘야합니다.
            // - DB 에 데이터를 추가해야합니다.

            FileCopyUtils.copy(file.getBytes(),new File(uploadPath,fileName));   //서버 측에 파일을 줍니다.

            Files uploadedFile = new Files();
            uploadedFile.setParentTable(parentTable);
            uploadedFile.setParentNo(parentNo);
            uploadedFile.setFileName(fileName);
            uploadedFile.setFilePath(filePath);
            uploadedFile.setOriginName(originName);
            uploadedFile.setFileSize(fileSize);   //long 타입입니다.
            uploadedFile.setFileCode(0);

            filesMapper.insert(uploadedFile);

        }

        return result;
  }

  @Override
  public int update(Board board) throws Exception {

    return boardMapper.update(board);
  }

  @Override
  public int delete(int boardNo) throws Exception {
    return boardMapper.delete(boardNo);
  }
  
}

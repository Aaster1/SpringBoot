package com.joeun.shop.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joeun.shop.dto.Product;
import com.joeun.shop.service.ProductService;


@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {


  @Autowired
  private ProductService productService;

  @GetMapping(value={"/",""})
  public String index(Model model,Product product) throws Exception{

      model.addAttribute("productList", productService.list());

      return "product/index";
  }
  

	@GetMapping("/img")
	public void responseImage(HttpServletResponse response,@RequestParam("file") String file) throws Exception {
		
		
		
		// 파일 경로
		String filePath = "C:/Users/tj-bu/Desktop/WEB/git/SpringBootVC/shop/src/main/resources"+file;
    ///static/img/test.jpeg
		
		
		
		// 헤더정보
		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType( MediaType.IMAGE_JPEG );					// 이미지로 응답
		headers.setContentType(MediaType.IMAGE_JPEG);
    		// 일반 프로그램 응답
		
		
		
		//headers.add("헤더명","값")
		//Content-Disposition
		//-inline : 웹페이지에서 출력(기본값)
		//-attachment : 첨부파일(다운로드)
		headers.add("Content-Disposition","inline;");	//형식(첨부파일), 파일명(test.jsp)
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			
			// commons-io 라이브러리
			// toByteArray() : 파일을 바이트코드로 변환
			FileCopyUtils.copy(fis, response.getOutputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
  
}

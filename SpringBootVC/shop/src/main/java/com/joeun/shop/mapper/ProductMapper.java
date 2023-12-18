package com.joeun.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.shop.dto.Product;

@Mapper
public interface ProductMapper {
  
public List<Product> list() throws Exception;

}

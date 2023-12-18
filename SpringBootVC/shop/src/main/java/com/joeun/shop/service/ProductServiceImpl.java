package com.joeun.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.shop.dto.Product;
import com.joeun.shop.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired
  private ProductMapper productMapper;

  @Override
  public List<Product> list() throws Exception {

    List<Product> productList = productMapper.list();
    return productList;

  }
  
  

}

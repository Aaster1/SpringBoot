package com.joeun.shop.service;

import java.util.List;

import com.joeun.shop.dto.Product;

public interface ProductService {
  
  public List<Product> list() throws Exception;

}

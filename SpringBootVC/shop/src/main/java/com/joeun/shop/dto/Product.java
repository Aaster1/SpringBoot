package com.joeun.shop.dto;

import lombok.Data;

@Data
public class Product {

  private String productId;
  private String name;
  private int unitPrice;
  private String description;
  private String manufacturer;
  private String category;
  private int unitsInStock;
  private String condition;
  private String file;
  private String quantity;
  
}

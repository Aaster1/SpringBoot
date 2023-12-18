package com.joeun.shop.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductIO {
    private int ioNo;
    private String productId;
    private int orderNo;
    private int amount;
    private String type;
    private Date ioDate;
    private String userId;
}

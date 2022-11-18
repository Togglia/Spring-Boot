package com.example.testproject.service;

import com.example.testproject.data.ProductDto;
//data를 저장 interface
public interface ProductService {

    ProductDto saveProduct(String productId,String productName,int productPrice,int productStock);

    ProductDto getProduct(String productId);
}

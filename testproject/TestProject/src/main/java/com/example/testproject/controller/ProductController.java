package com.example.testproject.controller;

import com.example.testproject.data.ProductDto;
import com.example.testproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {
    private ProductService productService; //객체생성
    //@Autowired 로 인해 new 사용안해도됨
    @Autowired //의존성 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }
    @PostMapping(value = "/product")
    public  ProductDto createProduct(@RequestBody ProductDto productDto){
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        return productService.saveProduct(productId,productName,productPrice,productStock);
    }
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }
}

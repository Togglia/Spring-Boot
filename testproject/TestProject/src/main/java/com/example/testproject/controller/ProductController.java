package com.example.testproject.controller;

import com.example.testproject.common.Constant;
import com.example.testproject.common.exception.TestProjectException;
import com.example.testproject.data.ProductDto;
import com.example.testproject.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService; //객체생성
    //@Autowired 로 인해 new 사용안해도됨
    @Autowired //의존성 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of testproject.","getProduct");
        ProductDto productDto = productService.getProduct(productId);//Service 가 처리된 이후
        LOGGER.info("[ProductController] Response :: productId= {},productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(),productDto.getProductName(),productDto.getProductPrice(),productDto.getProductStock(),(System.currentTimeMillis()-startTime));
        return productDto;
    }

    @PostMapping(value = "/product")
    public  ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        //@Valid 유효성 검증 : 설정한 범위 안에 들어오는가?아니면 error
        //Validation Code
        /*
        if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()){
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }
        */
        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService.saveProduct(productId,productName,productPrice,productStock);

        LOGGER.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}"
                ,response.getProductId(),response.getProductName(),response.getProductPrice(),response.getProductStock());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws TestProjectException{
        throw new TestProjectException(Constant.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN,"의도한 에러가 발생");
    }
}

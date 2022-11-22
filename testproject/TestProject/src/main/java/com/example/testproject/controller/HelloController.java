package com.example.testproject.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@RestController // 이 페이지가 controller의 역할을 함을 알 수 있다.
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @PostMapping("log-test")
    public void logTest(){
        LOGGER.trace("Trace Log");
        LOGGER.debug("debug Log");
        LOGGER.info("info Log");
        LOGGER.warn("warn Log");
        LOGGER.error("error Log");
    }
    @PostMapping("/exception")
    public void exceptionTest() throws Exception{
        throw new Exception(); //밑에있는 로직으로
    }
   /* @ExceptionHandler(value = Exception.class) //우선처리
    public ResponseEntity<Map<String,String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getMessage());
        LOGGER.info("Controller 내 ExceptionHandler 호출");

        //Map을 만들어서 아래를 클라이언트에 보냄
        Map<String, String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message","에러발생");

        return new ResponseEntity<>(map,responseHeaders,httpStatus);

    }
    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    /*@GetMapping("hello")
    public String hello(){
        return "Hello world!";
    }
*/

}

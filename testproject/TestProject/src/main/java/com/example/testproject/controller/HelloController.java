package com.example.testproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 페이지가 controller의 역할을 함을 알 수 있다.
public class HelloController {

    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    @GetMapping("hello")
    public String hello(){
        return "Hello world!";
    }


}

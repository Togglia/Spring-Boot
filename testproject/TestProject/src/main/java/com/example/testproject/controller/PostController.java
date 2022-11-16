package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
//post api : 리소스를 추가하기 위한 API
public class PostController {

    @PostMapping(value = "/default")
    public String postMethod(){
        return "hello world";
    }
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        //RequestBody : GetMapping 과 다르게 지정해주어야한다.
        // Map 객체<key , value> 객체이름
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map ->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }


}

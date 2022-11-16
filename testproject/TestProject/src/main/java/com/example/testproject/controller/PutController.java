package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @PutMapping(value = "/default")
    public String putMethod(){
        return "Hello world";
    }
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map ->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString(); ////memberDTO를 받아서 바로 String으로 받는 방식 --> toString의 형태로 임의 변환하여 출력
    }
    @PutMapping(value = "/member2")
    public MemberDTO putMemberDto2(@RequestBody MemberDTO memberDTO){
        return memberDTO; ////memberDTO 리턴 방식  --> json의 형식으로 나타나져 더 선호하는 방식
    }
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> putMemberDto3(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
        //memberDTO 받고 ResponseEntity를 씌워서 status(HttpStatus.ACCEPTED) 로 받고 body값에 memberDTO를 담아 리턴
        //ACCEPTED --> 202 코드로, 나옴 상황에 맞게끔 변경
    }

}

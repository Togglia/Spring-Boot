package com.example.testproject.service.impl;

import com.example.testproject.dto.MemberDTO;
import com.example.testproject.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);
    @Override
    public String getTestProject() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")//주소
                .path("/api/server/testproject")//경로
                .encode() //UTF-8
                .build() //return UriComponent
                .toUri();//component 이므로 Uri 객체로 바꿔줘야한다.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")//주소
                .path("/api/server/name")//경로
                .queryParam("name","kevin") //전송값
                .encode() //UTF-8
                .build() //return UriComponent
                .toUri();//component 이므로 Uri 객체로 바꿔줘야한다.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")//주소
                .path("/api/server/path-variable/{name}")//경로
                .encode() //UTF-8
                .build() //return UriComponent
                .expand("kevin")// 복수 값을 넣을 경우 , 표시
                .toUri();//component 이므로 Uri 객체로 바꿔줘야한다.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")//주소
                .path("/api/server/member")//경로
                .queryParam("name","kevin")//@requestParam 값 매핑
                .queryParam("email","kkk@kkk.com")
                .queryParam("organization","Seoul")
                .encode() //UTF-8
                .build() //return UriComponent
                .toUri();//component 이므로 Uri 객체로 바꿔줘야한다.

        //requestbody 쪽에 넣을 값들
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("Kevin!!");
        memberDTO.setEmail("nnn@nnn.com");
        memberDTO.setOrganization("Seoul!!");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO,
                MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")//주소
                .path("/api/server/add-header")//경로
                .encode() //UTF-8
                .build() //return UriComponent
                .toUri();//component 이므로 Uri 객체로 바꿔줘야한다.

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("Kevin");
        memberDTO.setEmail("kkk@kkk.com");
        memberDTO.setOrganization("Seoul");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("testproject-header", "testproject head")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity,
                MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}

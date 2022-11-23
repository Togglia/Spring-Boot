package com.example.testproject.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {
    @BeforeAll
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }
    @AfterAll
    static void AfterAll(){
        System.out.println("## AfterAll Annotation 호출 ##");
        System.out.println();
    }
    @BeforeEach
    void BeforeEach(){
        System.out.println("## BeforeEach Annotation 호출 ##");
        System.out.println();
    }
    @AfterEach
    void AfterEach(){
        System.out.println("## AfterEach Annotation 호출 ##");
        System.out.println();
    }
    @Test
    void Test1(){
        System.out.println("## Test1 Annotation 호출 ##");
        System.out.println();
    }
    @Test
    @DisplayName("Test Case2!!!")
    void Test2(){
        System.out.println("## Test2 시작 ##");
        System.out.println();
    }
    @Test
    @Disabled //테스트를 실행하지 않게 설정하는 어노테이션
    void Test3(){
        System.out.println("## Test3 시작 ##");
        System.out.println();
    }
}

package com.example.testproject.controller;

import com.example.testproject.data.ProductDto;
import com.example.testproject.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)//test 하고자 하는 컨트롤러
//@AutoConfigureMockMvc // 이게있으면 MockMvc 의존성 주입안해도됨
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //ProductController에서 잡고있는 Bean 객체에 대한 가짜 객체 생성
            //의존성 주입해줬으니까 만들어야함
    ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{
        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        //   productService 객체에서 productId 가 12315 이면 ProductDto 값을 리턴할것이다.
        given(productService.getProduct("12315")).willReturn(
                new ProductDto("15871","pen",5000,2000));

        String productId = "12315";

        //andExpect : 기대하는 값이 나왔는지 체크할 수 있는 메소드
        mockMvc.perform(
                get("/api/v1/product-api/product/"+productId))
                .andExpect(status().isOk())
                //return으로 받는 형태는 json형태의 body값으로 받기때문에
                //json의 키값이 존재하는지
                .andExpect(jsonPath("$.productId").exists())//json Path의 depth가 깊어지면 .을 추가하여 탐색가능
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());//테스트한내용 출력

        //verify : 해당 객체의 메소드가 실행되었는지 체크
        verify(productService).getProduct("12315");
    }
    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(productService.saveProduct("15871", "pen", 5000, 2000)).willReturn(
                new ProductDto("15871", "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder().productId("15871").productName("pen")
                .productPrice(5000).productStock(2000).build();
       // Gson gson = new Gson();//구글에서 만든 json형태
        //String content = gson.toJson(productDto); //productDto 객체를 json형태로 변경을 한 String 값 만듬

        // 아래 코드로 json 형태 변경 작업을 대체할 수 있음
         String json = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(
                        post("/api/v1/product-api/product")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        verify(productService).saveProduct("15871", "pen", 5000, 2000);
    }
}

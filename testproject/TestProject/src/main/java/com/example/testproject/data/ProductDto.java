package com.example.testproject.data;

import com.example.testproject.data.entity.ProductEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto  {

    @NotNull
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    @Min(value=500)
    @Max(value=3000000)
    private int productPrice;
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int productStock;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}

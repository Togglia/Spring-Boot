package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    //JpaRepository<Repository 가 사용할 엔티티, Id 값의 데이터타입>

}

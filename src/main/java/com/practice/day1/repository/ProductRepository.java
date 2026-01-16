package com.practice.day1.repository;

import com.practice.day1.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 재고가 특정 수량보다 적은 상품 찾기
    List<Product> findByStockLessThan(int stock);

    // 상품명에 특정 문자열이 포함된 상품 찾기 (LIKE 검색)
    List<Product> findByNameContaining(String name);
}
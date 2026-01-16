package com.practice.day1.service;

import com.practice.day1.domain.Product;
import com.practice.day1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 전체 상품 조회
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 상품 단건 조회
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));
    }

    // 상품 등록
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 재고 입고
    @Transactional
    public void addStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));
        product.addStock(quantity);
        // save() 불필요 - 더티 체킹으로 자동 저장
    }

    // 재고 출고
    @Transactional
    public void removeStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));
        product.removeStock(quantity);
    }

    // 재고 부족 상품 조회 (10개 미만)
    @Transactional(readOnly = true)
    public List<Product> getLowStockProducts() {
        return productRepository.findByStockLessThan(10);
    }

    // 상품명 검색
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
}
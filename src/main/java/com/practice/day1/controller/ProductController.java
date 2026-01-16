package com.practice.day1.controller;

import com.practice.day1.domain.Product;
import com.practice.day1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 루트 경로 → products로 redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    // ========== 화면 (Thymeleaf) ==========

    // 상품 목록 페이지
    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    // 상품 등록 폼 페이지
    @GetMapping("/products/new")
    public String newProductForm() {
        return "product-form";
    }

    // 상품 등록 처리
    @PostMapping("/products")
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    // ========== REST API (JSON) ==========

    // 전체 상품 조회 API
    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    // 단건 조회 API
    @GetMapping("/api/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 재고 입고 API
    @PostMapping("/api/products/{id}/add-stock")
    @ResponseBody
    public String addStock(@PathVariable Long id, @RequestParam int quantity) {
        productService.addStock(id, quantity);
        return "재고 입고 완료";
    }

    // 재고 출고 API
    @PostMapping("/api/products/{id}/remove-stock")
    @ResponseBody
    public String removeStock(@PathVariable Long id, @RequestParam int quantity) {
        productService.removeStock(id, quantity);
        return "재고 출고 완료";
    }

    // 재고 부족 상품 조회 API
    @GetMapping("/api/products/low-stock")
    @ResponseBody
    public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    // 상품명 검색 API
    @GetMapping("/api/products/search")
    @ResponseBody
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
}
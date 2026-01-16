package com.practice.day1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer price;

    private Integer stock;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // IllegalArgumentException: 피라미터 문제(예: 음수 입력)
    // IllegalStateException: 객체 상태 문제(예: 이미 삭제된 상품)
    public void addStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("음수로 입력할 수는 없습니다.");
        }
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        if (this.stock < quantity) {
            throw new IllegalStateException("재고가 부족합니다. 현재 재고: " + this.stock + ", 요청 수량: " + quantity);
        }
        this.stock -= quantity;
    }
}
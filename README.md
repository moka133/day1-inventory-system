# 상품 재고 관리 시스템

> Spring Boot 기반의 간단한 ERP 재고 관리 시스템

## 프로젝트 개요

ERP 시스템의 핵심 기능인 재고 관리를 구현한 프로젝트입니다.
상품 등록, 입출고 관리, 재고 현황 조회 기능을 제공합니다.

## 기술 스택

- **Backend**: Java 21, Spring Boot 4.0.1
- **ORM**: Spring Data JPA, Hibernate
- **Database**: H2 (In-Memory)
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5
- **Build Tool**: Gradle

## 주요 기능

### 상품 관리
- 상품 등록/조회/수정/삭제 (CRUD)
- 상품명 검색 기능

### 재고 관리
- 재고 입고 처리
- 재고 출고 처리
- 재고 부족 상품 조회 (10개 미만)

### REST API
- JSON 형식의 API 제공
- Thymeleaf SSR과 REST API 혼합 구조

## 프로젝트 구조
```
day1/
├── src/
│   ├── main/
│   │   ├── java/com/practice/day1/
│   │   │   ├── controller/      # Controller 레이어
│   │   │   ├── service/         # Service 레이어
│   │   │   ├── repository/      # Repository 레이어
│   │   │   └── domain/          # Entity 클래스
│   │   └── resources/
│   │       ├── templates/       # Thymeleaf 템플릿
│   │       └── application.yml  # 설정 파일
│   └── test/                    # 테스트 코드
├── build.gradle                 # Gradle 설정
└── README.md
```

## 실행 방법

### 1. 프로젝트 클론
```bash
git clone https://github.com/your-username/day1.git
cd day1
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3. 브라우저 접속
```
http://localhost:8080
```

### 4. H2 콘솔 접속 (선택사항)
```
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (빈칸)
```

## API 엔드포인트

### 화면 (Thymeleaf)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/products` | 상품 목록 페이지 |
| GET | `/products/new` | 상품 등록 폼 |
| POST | `/products` | 상품 등록 처리 |

### REST API (JSON)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/api/products` | 전체 상품 조회 |
| GET | `/api/products/{id}` | 특정 상품 조회 |
| POST | `/api/products/{id}/add-stock?quantity={n}` | 재고 입고 |
| POST | `/api/products/{id}/remove-stock?quantity={n}` | 재고 출고 |
| GET | `/api/products/low-stock` | 재고 부족 상품 조회 |
| GET | `/api/products/search?keyword={keyword}` | 상품명 검색 |

## 핵심 기술 구현

### 1. JPA Entity 설계
- `@PrePersist`, `@PreUpdate`를 활용한 날짜 자동 관리
- 비즈니스 로직을 Entity에 캡슐화 (`addStock`, `removeStock`)

### 2. 더티 체킹 (Dirty Checking)
- `@Transactional` 내에서 Entity 변경 시 자동 UPDATE
- `save()` 호출 불필요

### 3. Spring Data JPA 쿼리 메서드
- `findByStockLessThan(int stock)` - 메서드 이름으로 쿼리 자동 생성
- `findByNameContaining(String name)` - LIKE 검색

### 4. 예외 처리
- `Optional.orElseThrow()`를 활용한 안전한 조회
- 재고 부족 시 예외 발생으로 트랜잭션 롤백

## 학습 내용

### Entity & JPA
- JPA 기본 어노테이션 (`@Entity`, `@Id`, `@GeneratedValue`)
- 생명주기 콜백 (`@PrePersist`, `@PreUpdate`)
- 데이터 타입 선택 (`Integer` vs `int`)

### Repository
- `JpaRepository` 인터페이스 상속
- 쿼리 메서드 네이밍 규칙
- 커스텀 쿼리 vs 자동 생성 쿼리

### Service
- `@Transactional`의 역할과 중요성
- `readOnly = true` 최적화
- 더티 체킹 메커니즘

### Controller
- `@Controller` vs `@RestController`
- `@PathVariable` vs `@RequestParam`
- `Model`을 통한 뷰 데이터 전달
- PRG 패턴 (Post-Redirect-Get)

## 트러블슈팅

### Issue 1: Circular view path 에러
- **원인**: (해결한 원인 적기)
- **해결**: (해결 방법 적기)

## 향후 개선 계획

- [ ] 카테고리 기능 추가 (1:N 관계)
- [ ] 전역 예외 처리 (@ControllerAdvice)
- [ ] DTO 패턴 적용
- [ ] 페이징 처리 (Pageable)
- [ ] 단위 테스트 작성

## 작성자

**개발 기간**: 2026.01.14 ~ 2026.01.15 (2일)  
**GitHub**: [@moka133](https://github.com/moka133)

## License

This project is licensed under the MIT License.
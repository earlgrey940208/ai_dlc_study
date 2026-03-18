# Code Summary - Unit 1: Backend

## 생성된 파일 목록

### Project Setup
- `build.gradle.kts` - Gradle 빌드 설정
- `settings.gradle.kts` - 프로젝트 설정
- `src/main/resources/application.yml` - 개발 환경 설정 (H2)
- `src/main/resources/application-prod.yml` - 프로덕션 설정 (PostgreSQL)
- `TableOrderApplication.java` - Spring Boot 메인 클래스

### Entity (7개)
- `Admin.java`, `Category.java`, `Menu.java`, `TableInfo.java`
- `Order.java`, `OrderItem.java`, `OrderHistory.java`

### Repository (7개)
- `AdminRepository.java`, `CategoryRepository.java`, `MenuRepository.java`
- `TableInfoRepository.java`, `OrderRepository.java`, `OrderItemRepository.java`
- `OrderHistoryRepository.java`

### DTO
- Request: `LoginRequest`, `TableLoginRequest`, `CreateOrderRequest`, `UpdateOrderStatusRequest`, `SetupTableRequest`
- Response: `TokenResponse`, `OrderResponse`, `ErrorResponse`

### Security
- `JwtTokenProvider.java` - JWT 생성/검증
- `JwtAuthenticationFilter.java` - 요청별 JWT 필터
- `SecurityConfig.java` - 인증 규칙, CORS

### Service (5개)
- `AuthService.java` - 관리자/테이블 인증
- `MenuService.java` - 메뉴/카테고리 조회
- `OrderService.java` - 주문 CRUD + SSE 연동
- `TableService.java` - 테이블 관리 + 이용 완료
- `SseService.java` - SSE 구독/브로드캐스트

### Controller (5개)
- `AuthController.java`, `MenuController.java`, `OrderController.java`
- `TableController.java`, `SseController.java`

### Exception
- `GlobalExceptionHandler.java` - 전역 예외 처리

## Story 커버리지
- 11개 Story 전부 Backend 구현 완료
- US-3.1, US-3.2 (장바구니)는 Frontend 전용

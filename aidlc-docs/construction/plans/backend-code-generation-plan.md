# Code Generation Plan - Unit 1: Backend

## Unit Context
- **Unit**: Backend (Spring Boot API)
- **기술**: Java 17, Spring Boot 3.x, Gradle, JPA, Spring Security
- **Stories**: US-1.1, US-1.2, US-2.1, US-4.1, US-4.2, US-5.1, US-5.2, US-6.1, US-6.2, US-6.3, US-6.4
- **Code Location**: `backend/` (workspace root)
- **Approach**: Standard (non-TDD)

---

## Generation Steps

### Step 1: Project Structure Setup
- [x] Spring Boot 프로젝트 초기화 (build.gradle.kts, application.yml, application-prod.yml)
- [x] 패키지 구조 생성
- [x] Stories: N/A (인프라)

### Step 2: Entity Layer
- [x] Admin, Category, Menu, TableInfo, Order, OrderItem, OrderHistory 엔티티
- [x] Stories: 전체 (데이터 모델 기반)

### Step 3: Repository Layer
- [x] 각 Entity의 JpaRepository 인터페이스
- [x] 커스텀 쿼리 메서드 (findByTableId, findByActive 등)
- [x] Stories: 전체

### Step 4: DTO Layer
- [x] Request/Response DTO 클래스
- [x] Stories: 전체

### Step 5: Security Layer
- [x] JwtTokenProvider (JWT 생성/검증)
- [x] JwtAuthenticationFilter (요청별 JWT 검증)
- [x] SecurityConfig (URL별 인증 규칙, CORS)
- [x] Stories: US-1.1, US-1.2

### Step 6: AuthService + AuthController
- [x] 관리자 로그인, 테이블 태블릿 인증
- [x] Stories: US-1.1, US-1.2

### Step 7: MenuService + MenuController
- [x] 메뉴 목록 조회, 카테고리 목록 조회
- [x] Stories: US-2.1

### Step 8: OrderService + OrderController
- [x] 주문 생성, 조회, 상태 변경, 삭제
- [x] SSE 이벤트 발행 연동
- [x] Stories: US-4.1, US-4.2, US-5.2, US-6.2

### Step 9: TableService + TableController
- [x] 테이블 설정, 목록 조회, 이용 완료, 과거 내역
- [x] Stories: US-6.1, US-6.3, US-6.4

### Step 10: SseService + SseController
- [x] SSE 구독, 이벤트 브로드캐스트
- [x] Stories: US-5.1

### Step 11: GlobalExceptionHandler
- [x] 전역 예외 처리, 일관된 에러 응답
- [x] Stories: N/A (cross-cutting)

### Step 12: Documentation
- [ ] `aidlc-docs/construction/backend/code/code-summary.md` 생성
- [ ] Stories: N/A

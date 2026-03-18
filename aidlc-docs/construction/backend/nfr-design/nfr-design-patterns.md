# NFR Design Patterns - Unit 1: Backend

---

## 보안 패턴

### JWT 인증 필터
- **패턴**: Filter Chain (Spring Security OncePerRequestFilter)
- **적용**: 모든 API 요청에 JWT 검증 필터 적용
- **제외**: 로그인 API, 메뉴 조회 API는 필터 제외
- **구현**: SecurityFilterChain에서 URL별 인증 규칙 설정

### 비밀번호 해싱
- **패턴**: BCrypt (Spring Security BCryptPasswordEncoder)
- **적용**: Admin 비밀번호, TableInfo 비밀번호

### 역할 기반 접근 제어
- **패턴**: Role-Based Access Control (RBAC)
- **역할**: ADMIN (관리자), TABLE (고객 태블릿)
- **적용**: JWT payload의 role 필드로 API 접근 제어

---

## 실시간 통신 패턴

### SSE (Server-Sent Events)
- **패턴**: Observer / Publish-Subscribe
- **적용**: 주문 이벤트 → 관리자 대시보드 실시간 전달
- **구현**: SseEmitter 목록 관리, 이벤트 발생 시 브로드캐스트
- **재연결**: 클라이언트 EventSource 자동 재연결

---

## 데이터 접근 패턴

### Repository 패턴
- **패턴**: Spring Data JPA Repository
- **적용**: 모든 Entity에 대해 JpaRepository 인터페이스
- **장점**: CRUD 자동 생성, 쿼리 메서드 네이밍 규칙

### DTO 변환 패턴
- **패턴**: Request DTO → Entity → Response DTO
- **적용**: Controller에서 DTO 수신, Service에서 Entity 변환, 응답 시 DTO 반환
- **장점**: Entity 직접 노출 방지

---

## 에러 처리 패턴

### Global Exception Handler
- **패턴**: @RestControllerAdvice + @ExceptionHandler
- **적용**: 전역 예외 처리, 일관된 에러 응답 형식
- **응답 형식**: `{ "error": "메시지", "status": 400 }`

---

## 설정 패턴

### Profile 기반 설정
- **패턴**: Spring Profiles (application-dev.yml, application-prod.yml)
- **적용**: dev(H2) / prod(PostgreSQL) 환경 분리

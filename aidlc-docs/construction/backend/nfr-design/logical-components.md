# Logical Components - Unit 1: Backend

---

## 컴포넌트 구조

```
[Client Request]
      |
      v
[SecurityFilterChain] ── JWT 검증
      |
      v
[Controller Layer] ── 요청 DTO 수신, 유효성 검증
      |
      v
[Service Layer] ── 비즈니스 로직, 트랜잭션 관리
      |
      v
[Repository Layer] ── JPA 데이터 접근
      |
      v
[PostgreSQL / H2]
```

---

## 논리 컴포넌트 목록

| 컴포넌트 | 타입 | 책임 |
|---------|------|------|
| JwtTokenProvider | Security | JWT 생성/검증/파싱 |
| JwtAuthenticationFilter | Security | 요청별 JWT 검증 필터 |
| SecurityConfig | Config | URL별 인증 규칙, CORS 설정 |
| GlobalExceptionHandler | Cross-cutting | 전역 예외 처리 |
| BCryptPasswordEncoder | Security | 비밀번호 해싱/검증 |
| SseEmitterManager | Infra | SseEmitter 목록 관리 (ConcurrentHashMap) |

## 패키지 매핑

```
com.tableorder/
├── config/
│   └── SecurityConfig.java
├── security/
│   ├── JwtTokenProvider.java
│   └── JwtAuthenticationFilter.java
├── exception/
│   └── GlobalExceptionHandler.java
├── controller/
├── service/
├── repository/
├── entity/
└── dto/
    ├── request/
    └── response/
```

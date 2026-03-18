# Unit of Work

## 프로젝트 구조

- **저장소 전략**: Monorepo
- **분해 수준**: 3-Unit
- **개발 순서**: Backend 우선

```
<workspace-root>/
├── backend/          # Unit 1: Spring Boot
├── frontend/         # Unit 2: React
├── docker/           # Unit 3: Docker Compose, DB init
├── aidlc-docs/       # 문서
└── README.md
```

---

## Unit 1: Backend (Spring Boot API)

- **타입**: Service (독립 배포 가능)
- **기술**: Spring Boot (Java), JPA, SSE
- **책임**: REST API 전체, 비즈니스 로직, 인증, SSE 실시간 이벤트
- **포함 컴포넌트**: BC-1(Auth), BC-2(Menu), BC-3(Order), BC-4(Table), BC-5(Sse)
- **포함 서비스**: AuthService, MenuService, OrderService, TableService, SseService
- **개발 순서**: 1번째 (API 먼저 완성)

### 패키지 구조 (계층형)
```
com.tableorder/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── config/
└── TableOrderApplication.java
```

---

## Unit 2: Frontend (React SPA)

- **타입**: Module (Backend에 의존)
- **기술**: React, Axios/Fetch
- **책임**: 고객 주문 UI, 관리자 대시보드 UI, SSE 수신
- **포함 컴포넌트**: FC-1(CustomerApp), FC-2(AdminApp), FC-3(SharedComponents)
- **개발 순서**: 2번째 (Backend API 완성 후)

### 디렉토리 구조
```
frontend/src/
├── pages/
│   ├── customer/
│   └── admin/
├── components/
├── api/
├── hooks/
└── App.jsx
```

---

## Unit 3: Database & Infrastructure

- **타입**: Infrastructure
- **기술**: PostgreSQL, Docker Compose, (선택) Rancher
- **책임**: DB 스키마, Seed data, 컨테이너 구성, 배포 설정
- **포함 컴포넌트**: DC-1(PostgreSQL Database)
- **개발 순서**: 3번째 (Backend/Frontend 완성 후 통합)

### 구성 파일
```
docker/
├── docker-compose.yml
├── init.sql            # 스키마 + Seed data
└── Dockerfile.backend  # (필요 시)
```

# Unit of Work Dependency

## 의존성 매트릭스

```
              Unit 1      Unit 2      Unit 3
              Backend     Frontend    DB+Infra
Unit 1         -           -           ✓ (DB)
Unit 2         ✓ (API)     -           -
Unit 3         -           -           -
```

- Unit 1 (Backend) → Unit 3 (DB): Backend가 PostgreSQL에 의존
- Unit 2 (Frontend) → Unit 1 (Backend): Frontend가 REST API에 의존
- Unit 3 (DB+Infra): 독립적 (다른 Unit에 의존하지 않음)

## 개발 순서

```
Unit 3 (DB+Infra) → Unit 1 (Backend) → Unit 2 (Frontend)
```

실제 Construction Phase 진행 순서:
1. **Unit 1: Backend** - DB 스키마는 JPA entity로 자동 생성 가능, API 우선 개발
2. **Unit 2: Frontend** - Backend API 완성 후 연동
3. **Unit 3: DB+Infra** - Docker Compose로 전체 통합, Seed data, 배포 설정

> Note: Unit 1 개발 시 H2 in-memory DB로 로컬 테스트 가능. Unit 3에서 PostgreSQL로 전환.

## 통합 포인트

| From | To | 인터페이스 | 설명 |
|------|----|-----------|------|
| Frontend | Backend | REST API (HTTP) | 13개 API endpoint |
| Frontend | Backend | SSE | 실시간 주문 알림 (관리자) |
| Backend | PostgreSQL | JPA/JDBC | 데이터 영속화 |

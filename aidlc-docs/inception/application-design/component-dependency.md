# Component Dependency

## 의존성 매트릭스

```
                Auth  Menu  Order  Table  Sse
AuthService      -     -     -      -     -
MenuService      -     -     -      -     -
OrderService     -     -     -      -     ✓
TableService     -     -     ✓      -     -
SseService       -     -     -      -     -
```

## 데이터 흐름

```
[고객 태블릿]
    |
    ├── GET /api/menu/list ──────→ MenuService → MenuRepository → DB
    ├── POST /api/order/create ──→ OrderService → OrderRepository → DB
    │                                    └──→ SseService (이벤트 발행)
    └── GET /api/order/list ─────→ OrderService → OrderRepository → DB

[관리자 브라우저]
    |
    ├── POST /api/auth/login ────→ AuthService → AdminRepository → DB
    ├── GET /api/sse/subscribe ──→ SseService (SSE 연결)
    ├── PUT /api/order/update-status → OrderService → DB → SseService
    └── POST /api/table/complete → TableService → OrderHistoryRepository → DB
```

## 통신 패턴

| From | To | 방식 | 설명 |
|------|----|------|------|
| Frontend → Backend | REST API | HTTP | 모든 API 호출 |
| Backend → Frontend (관리자) | SSE | Server-Sent Events | 실시간 주문 알림 |
| OrderService → SseService | 내부 호출 | Method call | 주문 이벤트 발행 |
| TableService → OrderRepository | 내부 호출 | Method call | 이용 완료 시 주문 이동 |

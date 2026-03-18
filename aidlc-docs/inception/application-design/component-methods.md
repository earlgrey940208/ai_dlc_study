# Component Methods

> API 스타일: 기능 중심 | Backend 패키지: 계층형 (controller/service/repository/entity)

---

## AuthController

| Method | HTTP | URL | Input | Output | 설명 |
|--------|------|-----|-------|--------|------|
| login | POST | `/api/auth/login` | storeCode, username, password | JWT token | 관리자 로그인 |
| tableLogin | POST | `/api/auth/table-login` | storeCode, tableNumber, password | session token | 테이블 태블릿 인증 |

## MenuController

| Method | HTTP | URL | Input | Output | 설명 |
|--------|------|-----|-------|--------|------|
| getMenuList | GET | `/api/menu/list` | storeId, categoryId(optional) | Menu[] | 메뉴 목록 조회 |
| getCategoryList | GET | `/api/menu/categories` | storeId | Category[] | 카테고리 목록 조회 |

## OrderController

| Method | HTTP | URL | Input | Output | 설명 |
|--------|------|-----|-------|--------|------|
| createOrder | POST | `/api/order/create` | tableSessionId, items[] | Order | 주문 생성 |
| getOrderList | GET | `/api/order/list` | tableSessionId | Order[] | 현재 세션 주문 조회 |
| getOrdersByTable | GET | `/api/order/list-by-table` | tableId | Order[] | 테이블별 주문 조회 (관리자) |
| updateOrderStatus | PUT | `/api/order/update-status` | orderId, status | Order | 주문 상태 변경 |
| deleteOrder | DELETE | `/api/order/delete` | orderId | void | 주문 삭제 (관리자) |

## TableController

| Method | HTTP | URL | Input | Output | 설명 |
|--------|------|-----|-------|--------|------|
| setupTable | POST | `/api/table/setup` | storeId, tableNumber, password | TableInfo | 테이블 초기 설정 |
| getTableList | GET | `/api/table/list` | storeId | TableInfo[] | 테이블 목록 조회 |
| completeTable | POST | `/api/table/complete` | tableId | void | 이용 완료 처리 |
| getTableHistory | GET | `/api/table/history` | tableId, date(optional) | OrderHistory[] | 과거 내역 조회 |

## SseController

| Method | HTTP | URL | Input | Output | 설명 |
|--------|------|-----|-------|--------|------|
| subscribe | GET | `/api/sse/subscribe` | storeId | SseEmitter | SSE 연결 (관리자 대시보드) |

---

> Note: 상세 비즈니스 로직은 Functional Design (Construction Phase)에서 정의

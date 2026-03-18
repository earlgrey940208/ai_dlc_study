# Components

## Frontend Components

### FC-1: CustomerApp
- **목적**: 고객용 주문 인터페이스
- **책임**: 메뉴 조회, 장바구니 관리, 주문 생성, 주문 내역 조회
- **라우트**: `/customer/*`

### FC-2: AdminApp
- **목적**: 관리자용 매장 관리 인터페이스
- **책임**: 로그인, 실시간 주문 모니터링, 테이블 관리, 과거 내역 조회
- **라우트**: `/admin/*`

### FC-3: SharedComponents
- **목적**: 공통 UI 컴포넌트 및 유틸리티
- **책임**: API 호출 모듈, 공통 레이아웃, 에러 처리

---

## Backend Components

### BC-1: AuthController
- **목적**: 인증 처리
- **책임**: 관리자 로그인, JWT 발급, 테이블 태블릿 자동 인증

### BC-2: MenuController
- **목적**: 메뉴 데이터 제공
- **책임**: 카테고리별 메뉴 조회

### BC-3: OrderController
- **목적**: 주문 처리
- **책임**: 주문 생성, 주문 조회, 주문 상태 변경, 주문 삭제

### BC-4: TableController
- **목적**: 테이블 관리
- **책임**: 테이블 초기 설정, 이용 완료 처리, 과거 내역 조회

### BC-5: SseController
- **목적**: 실시간 이벤트 스트리밍
- **책임**: SSE 연결 관리, 주문 이벤트 브로드캐스트

---

## Data Components

### DC-1: PostgreSQL Database
- **목적**: 영구 데이터 저장
- **테이블**: Store, Admin, TableInfo, TableSession, Menu, Category, Order, OrderItem, OrderHistory

# Services

> 계층형 구조: Controller → Service → Repository

---

## AuthService
- **책임**: 인증 로직, JWT 토큰 생성/검증
- **의존**: AdminRepository, TableSessionRepository
- **주요 로직**:
  - 관리자 로그인 검증 및 JWT 발급
  - 테이블 태블릿 인증 및 세션 토큰 발급

## MenuService
- **책임**: 메뉴 데이터 조회
- **의존**: MenuRepository, CategoryRepository
- **주요 로직**:
  - 카테고리별 메뉴 필터링
  - 메뉴 상세 정보 조합

## OrderService
- **책임**: 주문 생성/조회/상태 관리
- **의존**: OrderRepository, OrderItemRepository, TableSessionRepository, SseService
- **주요 로직**:
  - 주문 생성 시 SSE 이벤트 발행
  - 주문 상태 변경 시 SSE 이벤트 발행
  - 주문 삭제 및 총 금액 재계산

## TableService
- **책임**: 테이블 설정/세션 관리
- **의존**: TableInfoRepository, TableSessionRepository, OrderRepository, OrderHistoryRepository
- **주요 로직**:
  - 테이블 초기 설정 및 세션 생성
  - 이용 완료 시 주문 → OrderHistory 이동 및 리셋
  - 과거 내역 조회

## SseService
- **책임**: SSE 연결 관리 및 이벤트 브로드캐스트
- **의존**: 없음 (다른 서비스에서 호출됨)
- **주요 로직**:
  - 매장별 SseEmitter 관리
  - 주문 이벤트 브로드캐스트 (신규 주문, 상태 변경, 삭제)

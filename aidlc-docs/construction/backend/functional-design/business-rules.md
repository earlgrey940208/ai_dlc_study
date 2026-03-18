# Business Rules - Unit 1: Backend

---

## 유효성 검증 규칙

| 대상 | 규칙 | 에러 |
|------|------|------|
| username | not blank | 400 Bad Request |
| password | not blank | 400 Bad Request |
| tableNumber | 1 이상 정수, unique | 400 Bad Request |
| 주문 items | 1개 이상 필수 | 400 Bad Request |
| item.menuId | 존재하는 Menu | 404 Not Found |
| item.quantity | 1 이상 정수 | 400 Bad Request |
| orderId | 존재하는 Order | 404 Not Found |
| tableId | 존재하는 TableInfo | 404 Not Found |

---

## 상태 전이 규칙

### 주문 상태
```
PENDING → PREPARING → COMPLETED (순방향만)
```
- PENDING → PREPARING: ✅
- PREPARING → COMPLETED: ✅
- PENDING → COMPLETED: ❌ (건너뛰기 불가)
- COMPLETED → PENDING: ❌ (역방향 불가)
- 잘못된 전이 시: 400 Bad Request

### 테이블 활성 상태
```
active=false → active=true (태블릿 인증 시 자동 활성화)
active=true → active=false (관리자 이용 완료 처리)
```

---

## 인증 규칙

| 규칙 | 설명 |
|------|------|
| 관리자 API | role=ADMIN 필수 |
| 고객 API | role=TABLE + 유효한 tableId 필수 |
| 주문 생성 | 해당 테이블 active=true 필수 |
| 메뉴 조회 | 인증 불필요 |

---

## 비즈니스 규칙

| 규칙 | 설명 |
|------|------|
| 주문 금액 | totalAmount = SUM(price * quantity), 서버에서 계산 |
| 메뉴 스냅샷 | OrderItem에 주문 시점의 menuName, price 저장 |
| 주문 삭제 | Hard Delete (DB에서 완전 삭제) |
| 이용 완료 | 주문 상태 무관하게 처리 가능 |
| 이용 완료 시 | Order→OrderHistory JSON 변환 후 Order 삭제, active=false |
| 테이블 번호 | unique (중복 불가) |

---

## 에러 처리

| HTTP | 상황 |
|------|------|
| 400 | 유효성 검증 실패, 잘못된 상태 전이 |
| 401 | 인증 실패 (잘못된 자격증명) |
| 403 | 권한 부족 (role 불일치, 비활성 테이블에서 주문) |
| 404 | 리소스 없음 (메뉴, 주문, 테이블) |
| 500 | 서버 내부 오류 |

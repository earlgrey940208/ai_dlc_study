# Business Logic Model - Unit 1: Backend

---

## 1. 인증 (AuthService)

### 관리자 로그인
1. username으로 Admin 조회 → 없으면 401
2. 비밀번호 해시 비교 → 불일치 시 401
3. JWT 생성 (payload: adminId, role="ADMIN")
4. JWT 반환

### 테이블 태블릿 인증
1. tableNumber로 TableInfo 조회 → 없으면 401
2. 비밀번호 해시 비교 → 불일치 시 401
3. active=false이면 active=true로 변경 (자동 활성화)
4. JWT 생성 (payload: tableId, role="TABLE")
5. JWT 반환

---

## 2. 메뉴 조회 (MenuService)

### 메뉴 목록 조회
1. Category 목록 조회 (sortOrder 정렬)
2. categoryId 지정 시 해당 카테고리 메뉴만 필터
3. 미지정 시 전체 메뉴 반환

### 카테고리 목록 조회
1. Category 목록 조회 (sortOrder 정렬)

---

## 3. 주문 (OrderService)

### 주문 생성
1. tableId로 TableInfo 조회, active=true 확인 → 아니면 403
2. items 배열 순회: menuId로 Menu 조회, 존재 확인
3. Order 생성 (status=PENDING)
4. OrderItem 생성 (menuName, price는 Menu에서 스냅샷 복사)
5. totalAmount 계산 (각 item의 price * quantity 합산)
6. SSE 이벤트 발행: NEW_ORDER (orderId, tableNumber, totalAmount)
7. Order 반환

### 주문 목록 조회 (테이블별)
1. tableId로 Order 목록 조회 (createdAt 정렬)
2. 각 Order에 OrderItem 포함하여 반환

### 주문 상태 변경
1. orderId로 Order 조회 → 없으면 404
2. 상태 전이 검증: PENDING→PREPARING→COMPLETED (순방향만)
3. 상태 업데이트
4. SSE 이벤트 발행: ORDER_STATUS_CHANGED (orderId, newStatus)
5. 업데이트된 Order 반환

### 주문 삭제
1. orderId로 Order 조회 → 없으면 404
2. OrderItem 전체 삭제
3. Order 삭제 (Hard Delete)
4. SSE 이벤트 발행: ORDER_DELETED (orderId, tableNumber)

---

## 4. 테이블 관리 (TableService)

### 테이블 초기 설정
1. tableNumber 중복 확인
2. 비밀번호 해싱
3. TableInfo 생성 (active=true)
4. TableInfo 반환

### 테이블 목록 조회
1. TableInfo 목록 조회
2. 각 테이블의 현재 주문 요약 포함

### 테이블 이용 완료
1. tableId로 TableInfo 조회 (active=true 확인)
2. 해당 테이블의 모든 Order + OrderItem 조회
3. OrderHistory 생성 (orderData = 주문 내역 JSON 직렬화, totalAmount = 전체 합산)
4. Order + OrderItem 삭제
5. TableInfo.active = false

### 과거 내역 조회
1. tableId로 OrderHistory 목록 조회 (completedAt 역순)
2. date 파라미터 있으면 해당 날짜 필터

---

## 5. SSE (SseService)

### SSE 구독
1. 전역 SseEmitter 목록 관리 (ConcurrentHashMap)
2. 새 SseEmitter 생성 (timeout 설정)
3. 연결 종료/타임아웃 시 목록에서 제거

### 이벤트 브로드캐스트
1. 전체 SseEmitter 목록 조회
2. 각 Emitter에 이벤트 전송
3. 전송 실패 시 해당 Emitter 제거

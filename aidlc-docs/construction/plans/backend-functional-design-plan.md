# Functional Design Plan - Unit 1: Backend

## 개요
Spring Boot Backend의 상세 비즈니스 로직, 도메인 모델, 비즈니스 규칙을 설계합니다.

---

## Plan

### Step 1: 도메인 엔티티 설계
- [x] Entity 정의 (Store, Admin, TableInfo, TableSession, Menu, Category, Order, OrderItem, OrderHistory)
- [x] Entity 간 관계 매핑 (1:N, N:1 등)
- [x] `domain-entities.md` 생성

### Step 2: 비즈니스 로직 모델 설계
- [x] 인증 로직 (관리자 로그인, 테이블 태블릿 인증)
- [x] 주문 프로세스 (생성 → 상태변경 → 삭제)
- [x] 테이블 세션 관리 (설정 → 이용 → 완료 → 이력 이동)
- [x] SSE 이벤트 발행 로직
- [x] `business-logic-model.md` 생성

### Step 3: 비즈니스 규칙 설계
- [x] 유효성 검증 규칙
- [x] 상태 전이 규칙
- [x] 에러 처리 규칙
- [x] `business-rules.md` 생성

---

## Questions

아래 질문에 답변해주세요.

## Question 1
주문 상태 전이 규칙을 어떻게 하시겠습니까?

A) 순방향만 허용: 대기중 → 준비중 → 완료 (역방향 불가)
B) 자유 전이: 어떤 상태에서든 다른 상태로 변경 가능
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 2
테이블 이용 완료 시 주문 상태 제한이 필요한가요?

A) 제한 없음 - 어떤 상태의 주문이든 이용 완료 가능 (MVP 단순화)
B) 모든 주문이 "완료" 상태여야만 이용 완료 가능
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 3
JWT 토큰에 어떤 정보를 포함하시겠습니까?

A) 최소 정보: storeId, adminId, role (관리자) / storeId, tableId, sessionId (테이블)
B) 확장 정보: 위 + storeName, tableName 등 추가 정보 포함
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 4
주문 삭제 시 처리 방식은?

A) Hard Delete - DB에서 완전 삭제
B) Soft Delete - 삭제 플래그만 변경 (deleted=true), 데이터 보존
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 5
테이블 세션 만료(16시간) 처리 방식은?

A) 서버 검증만 - API 호출 시 세션 만료 여부 체크, 만료 시 재인증 요구
B) 스케줄러 - 백그라운드에서 주기적으로 만료 세션 정리
C) Other (please describe after [Answer]: tag below)

[Answer]: A

# User Stories

> Feature 기반 분류 | Acceptance Criteria: 간결 (Given/When/Then 1~2개) | 우선순위: High/Medium/Low

---

## Feature 1: 인증 (Authentication)

### US-1.1: 테이블 태블릿 자동 로그인
- **As a** 고객
- **I want to** 테이블 태블릿에서 별도 로그인 없이 자동으로 인증되기를
- **So that** 바로 주문을 시작할 수 있다
- **Priority**: High
- **Persona**: 고객

**Acceptance Criteria**:
- Given 관리자가 테이블 초기 설정을 완료했을 때, When 고객이 태블릿을 열면, Then 자동 로그인되어 메뉴 화면이 표시된다

### US-1.2: 관리자 로그인
- **As a** 매장 관리자
- **I want to** 매장 식별자와 계정으로 로그인하기를
- **So that** 관리 시스템에 접근할 수 있다
- **Priority**: High
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 유효한 매장 식별자와 계정 정보가 있을 때, When 로그인하면, Then JWT 토큰이 발급되고 16시간 동안 세션이 유지된다

---

## Feature 2: 메뉴 조회 (Menu)

### US-2.1: 카테고리별 메뉴 조회
- **As a** 고객
- **I want to** 카테고리별로 메뉴를 탐색하기를
- **So that** 원하는 메뉴를 쉽게 찾을 수 있다
- **Priority**: High
- **Persona**: 고객

**Acceptance Criteria**:
- Given 메뉴 화면에 있을 때, When 카테고리를 선택하면, Then 해당 카테고리의 메뉴가 카드 형태로 표시된다 (메뉴명, 가격, 설명, 이미지)

---

## Feature 3: 장바구니 (Cart)

### US-3.1: 장바구니에 메뉴 추가
- **As a** 고객
- **I want to** 메뉴를 장바구니에 담기를
- **So that** 여러 메뉴를 한 번에 주문할 수 있다
- **Priority**: High
- **Persona**: 고객

**Acceptance Criteria**:
- Given 메뉴 화면에서 메뉴를 선택했을 때, When 장바구니에 추가하면, Then 장바구니에 메뉴가 담기고 총 금액이 업데이트된다

### US-3.2: 장바구니 수량 조절 및 삭제
- **As a** 고객
- **I want to** 장바구니의 메뉴 수량을 변경하거나 삭제하기를
- **So that** 주문 전에 내용을 수정할 수 있다
- **Priority**: Medium
- **Persona**: 고객

**Acceptance Criteria**:
- Given 장바구니에 메뉴가 있을 때, When 수량을 변경하거나 삭제하면, Then 총 금액이 실시간으로 재계산된다

---

## Feature 4: 주문 (Order)

### US-4.1: 주문 생성
- **As a** 고객
- **I want to** 장바구니의 메뉴를 주문하기를
- **So that** 매장에서 음식을 받을 수 있다
- **Priority**: High
- **Persona**: 고객

**Acceptance Criteria**:
- Given 장바구니에 메뉴가 있을 때, When 주문 확정 버튼을 누르면, Then 주문 번호가 표시되고 장바구니가 비워지며 메뉴 화면으로 돌아간다

### US-4.2: 주문 내역 조회
- **As a** 고객
- **I want to** 현재 세션의 주문 내역을 확인하기를
- **So that** 주문 상태를 알 수 있다
- **Priority**: Medium
- **Persona**: 고객

**Acceptance Criteria**:
- Given 주문을 완료했을 때, When 주문 내역 화면을 열면, Then 현재 세션의 주문 목록이 시간순으로 표시된다 (주문 번호, 메뉴, 금액, 상태)

---

## Feature 5: 실시간 주문 모니터링 (Order Monitoring)

### US-5.1: 실시간 주문 대시보드
- **As a** 매장 관리자
- **I want to** 들어오는 주문을 실시간으로 확인하기를
- **So that** 주문을 놓치지 않고 처리할 수 있다
- **Priority**: High
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 관리자 대시보드에 있을 때, When 새 주문이 들어오면, Then 2초 이내에 테이블별 카드에 주문이 표시되고 시각적으로 강조된다

### US-5.2: 주문 상태 변경
- **As a** 매장 관리자
- **I want to** 주문 상태를 변경하기를 (대기중 → 준비중 → 완료)
- **So that** 주문 처리 진행 상황을 관리할 수 있다
- **Priority**: High
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 주문 카드가 표시되어 있을 때, When 상태를 변경하면, Then 즉시 반영되고 고객 측에서도 상태가 업데이트된다

---

## Feature 6: 테이블 관리 (Table Management)

### US-6.1: 테이블 초기 설정
- **As a** 매장 관리자
- **I want to** 테이블 번호와 비밀번호를 설정하기를
- **So that** 고객 태블릿이 자동 로그인할 수 있다
- **Priority**: High
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 관리자가 로그인한 상태에서, When 테이블 번호와 비밀번호를 설정하면, Then 해당 테이블의 세션이 생성되고 태블릿 자동 로그인이 활성화된다

### US-6.2: 주문 삭제
- **As a** 매장 관리자
- **I want to** 특정 주문을 삭제하기를
- **So that** 잘못된 주문을 정정할 수 있다
- **Priority**: Medium
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 테이블에 주문이 있을 때, When 주문 삭제를 확인하면, Then 주문이 삭제되고 테이블 총 주문액이 재계산된다

### US-6.3: 테이블 이용 완료
- **As a** 매장 관리자
- **I want to** 테이블 이용 완료 처리를 하기를
- **So that** 새 고객이 깨끗한 상태에서 시작할 수 있다
- **Priority**: High
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 테이블에 주문 내역이 있을 때, When 이용 완료를 처리하면, Then 주문이 과거 이력으로 이동되고 테이블이 리셋된다

### US-6.4: 과거 주문 내역 조회
- **As a** 매장 관리자
- **I want to** 테이블별 과거 주문 내역을 조회하기를
- **So that** 이전 매출과 주문 이력을 확인할 수 있다
- **Priority**: Low
- **Persona**: 관리자

**Acceptance Criteria**:
- Given 관리자 대시보드에서, When 과거 내역 버튼을 클릭하면, Then 해당 테이블의 과거 주문 목록이 시간 역순으로 표시된다

---

## Story Summary

| Priority | Count | Stories |
|----------|-------|---------|
| High | 8 | US-1.1, US-1.2, US-2.1, US-3.1, US-4.1, US-5.1, US-5.2, US-6.1, US-6.3 |
| Medium | 3 | US-3.2, US-4.2, US-6.2 |
| Low | 1 | US-6.4 |
| **Total** | **12** | |

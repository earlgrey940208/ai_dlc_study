# Code Generation Plan - Unit 2: Frontend

## Unit Context
- **Unit**: Frontend (React SPA)
- **기술**: React, Axios, React Router
- **Stories**: US-1.1, US-1.2, US-2.1, US-3.1, US-3.2, US-4.1, US-4.2, US-5.1, US-5.2, US-6.1, US-6.2, US-6.3, US-6.4
- **Code Location**: `frontend/` (workspace root)
- **Approach**: Standard (non-TDD)

---

## Generation Steps

### Step 1: Project Setup
- [ ] React 프로젝트 초기화 (package.json, vite config)
- [ ] 공통 API 모듈, 라우터 설정
- [ ] Stories: N/A (인프라)

### Step 2: 고객 - 메뉴 조회 페이지
- [ ] 카테고리 탭 + 메뉴 카드 리스트
- [ ] Stories: US-2.1

### Step 3: 고객 - 장바구니
- [ ] 장바구니 추가/삭제/수량 조절, localStorage 저장
- [ ] Stories: US-3.1, US-3.2

### Step 4: 고객 - 주문 생성 및 내역
- [ ] 주문 확정, 주문 내역 조회
- [ ] Stories: US-4.1, US-4.2

### Step 5: 고객 - 태블릿 인증
- [ ] 테이블 번호 + 비밀번호 로그인, 토큰 저장
- [ ] Stories: US-1.1

### Step 6: 관리자 - 로그인
- [ ] 관리자 로그인 페이지
- [ ] Stories: US-1.2

### Step 7: 관리자 - 실시간 주문 대시보드
- [ ] 테이블별 주문 카드, SSE 연동, 상태 변경
- [ ] Stories: US-5.1, US-5.2

### Step 8: 관리자 - 테이블 관리
- [ ] 테이블 설정, 주문 삭제, 이용 완료, 과거 내역
- [ ] Stories: US-6.1, US-6.2, US-6.3, US-6.4

### Step 9: Documentation
- [ ] `aidlc-docs/construction/frontend/code/code-summary.md` 생성

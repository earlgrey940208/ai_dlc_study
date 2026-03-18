# Application Design Plan

## Design Checklist
- [x] Step 1: 사용자 답변 수집
- [x] Step 2: 답변 분석
- [x] Step 3: components.md 생성
- [x] Step 4: component-methods.md 생성
- [x] Step 5: services.md 생성
- [x] Step 6: component-dependency.md 생성
- [x] Step 7: 설계 검증 및 완료

---

## Questions

### Question 1: API 설계 스타일
REST API 설계 시 URL 구조를 어떻게 할까요?

A) 리소스 중심 - `/api/stores/{storeId}/tables/{tableId}/orders`
B) 기능 중심 - `/api/order/create`, `/api/order/list`
C) AI 추천에 맡기기

[Answer]:B

---

### Question 2: Frontend 구조
React 프로젝트 구조를 어떻게 할까요?

A) 단일 React 앱 - 고객용/관리자용을 라우팅으로 분리
B) 별도 React 앱 2개 - 고객용 앱 + 관리자용 앱
C) AI 추천에 맡기기

[Answer]:A

---

### Question 3: Backend 패키지 구조
Spring Boot 패키지 구조를 어떻게 할까요?

A) 계층형 - controller/service/repository/entity 패키지
B) 도메인형 - order/menu/table/auth 도메인별 패키지
C) AI 추천에 맡기기

[Answer]:A

---

답변 완료 후 알려주세요!

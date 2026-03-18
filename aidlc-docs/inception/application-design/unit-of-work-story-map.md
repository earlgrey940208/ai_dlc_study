# Unit of Work - Story Map

## Story → Unit 매핑

| Story | 제목 | Priority | Unit 1 (Backend) | Unit 2 (Frontend) | Unit 3 (DB+Infra) |
|-------|------|----------|:-:|:-:|:-:|
| US-1.1 | 테이블 태블릿 자동 로그인 | High | ✓ | ✓ | |
| US-1.2 | 관리자 로그인 | High | ✓ | ✓ | |
| US-2.1 | 카테고리별 메뉴 조회 | High | ✓ | ✓ | ✓ (Seed) |
| US-3.1 | 장바구니에 메뉴 추가 | High | | ✓ | |
| US-3.2 | 장바구니 수량 조절 및 삭제 | Medium | | ✓ | |
| US-4.1 | 주문 생성 | High | ✓ | ✓ | |
| US-4.2 | 주문 내역 조회 | Medium | ✓ | ✓ | |
| US-5.1 | 실시간 주문 대시보드 | High | ✓ | ✓ | |
| US-5.2 | 주문 상태 변경 | High | ✓ | ✓ | |
| US-6.1 | 테이블 초기 설정 | High | ✓ | ✓ | |
| US-6.2 | 주문 삭제 | Medium | ✓ | ✓ | |
| US-6.3 | 테이블 이용 완료 | High | ✓ | ✓ | |
| US-6.4 | 과거 주문 내역 조회 | Low | ✓ | ✓ | |

## Unit별 Story 수

| Unit | Story 수 | 비고 |
|------|---------|------|
| Unit 1: Backend | 11 | 장바구니(US-3.1, US-3.2)는 클라이언트 전용 |
| Unit 2: Frontend | 13 (전체) | 모든 Story에 UI 필요 |
| Unit 3: DB+Infra | 1 | Seed data (메뉴/카테고리 초기 데이터) |

## 검증

- ✅ 12개 Story 전부 최소 1개 Unit에 할당됨
- ✅ 누락된 Story 없음
- ✅ 장바구니(US-3.1, US-3.2)는 Frontend 전용 (클라이언트 로컬 저장)으로 올바르게 분류

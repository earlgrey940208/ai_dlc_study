# Unit of Work Plan

## 개요
테이블오더 서비스를 개발 가능한 단위(Unit of Work)로 분해합니다.

---

## Part 1: Planning

### Step 1: 시스템 분해 전략 결정

- [x] 프로젝트 구조 및 배포 모델 결정
- [x] Unit 경계 정의

### Step 2: Unit 정의 및 책임 할당

- [x] 각 Unit의 범위, 책임, 포함 컴포넌트 정의
- [x] `unit-of-work.md` 생성

### Step 3: Unit 간 의존성 분석

- [x] Unit 간 의존성 매트릭스 작성
- [x] 개발 순서 결정
- [x] `unit-of-work-dependency.md` 생성

### Step 4: Story-Unit 매핑

- [x] 12개 User Story를 Unit에 할당
- [x] 누락된 Story 없는지 검증
- [x] `unit-of-work-story-map.md` 생성

### Step 5: 검증 및 완료

- [x] Unit 경계 및 의존성 검증
- [x] 모든 Story가 Unit에 할당되었는지 확인

---

## Questions

아래 질문에 답변해주세요.

## Question 1
프로젝트 배포 단위를 어떻게 구성하시겠습니까?

A) Monorepo - Frontend와 Backend를 하나의 저장소에서 관리 (디렉토리 분리: `/frontend`, `/backend`)
B) Polyrepo - Frontend와 Backend를 별도 저장소로 분리
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 2
Unit of Work 분해 수준을 어떻게 하시겠습니까?

A) 3-Unit 분해: Frontend / Backend / Database+Infra (권장 - MVP에 적합, 관리 용이)
B) Feature 기반 분해: 인증 / 메뉴 / 주문 / 테이블관리 / 실시간모니터링 / Infra (세밀한 단위)
C) 2-Unit 분해: Application(Frontend+Backend) / Infrastructure (가장 단순)
D) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 3
개발 진행 순서에 대한 선호가 있으신가요?

A) Backend 우선 - API 먼저 완성 후 Frontend 연동 (안정적, 테스트 용이)
B) Frontend 우선 - UI/UX 먼저 구현 후 Backend 연동 (빠른 시각적 확인)
C) 병렬 개발 - Frontend와 Backend를 동시에 진행 (빠르지만 통합 리스크)
D) Other (please describe after [Answer]: tag below)

[Answer]: A

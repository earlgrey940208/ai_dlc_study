# Infrastructure Design Plan - Unit 1: Backend

## 개요
Backend 서비스의 인프라 구성 및 배포 아키텍처를 설계합니다.

---

## Plan

### Step 1: 컨테이너 구성 설계
- [x] Docker Compose 서비스 정의
- [x] 네트워크/포트 매핑
- [x] `infrastructure-design.md` 생성

### Step 2: 배포 아키텍처 설계
- [x] 로컬 개발 환경
- [x] Docker Compose 배포 환경
- [x] Rancher 배포 (선택)
- [x] `deployment-architecture.md` 생성

---

## Questions

## Question 1
Frontend 서빙 방식은?

A) Nginx 컨테이너에서 정적 파일 서빙 (프로덕션 방식, React 빌드 결과물 서빙)
B) Node.js dev server (개발용, hot reload 가능하지만 프로덕션 부적합)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 2
Backend 포트 번호는?

A) 8080 (Spring Boot 기본)
B) 3000
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 3
PostgreSQL 데이터 영속화는?

A) Docker Volume 사용 (컨테이너 재시작해도 데이터 유지)
B) 영속화 없음 (컨테이너 삭제 시 데이터 초기화 - MVP 단순화)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

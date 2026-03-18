# NFR Requirements Plan - Unit 1: Backend

## 개요
Backend 서비스의 비기능 요구사항(NFR)과 기술 스택을 결정합니다.

---

## Plan

### Step 1: NFR 요구사항 정의
- [x] 성능 요구사항
- [x] 보안 요구사항
- [x] 가용성/안정성 요구사항
- [x] 유지보수성 요구사항
- [x] `nfr-requirements.md` 생성

### Step 2: 기술 스택 결정
- [x] Backend 프레임워크 상세 버전
- [x] 빌드 도구
- [x] 주요 라이브러리
- [x] `tech-stack-decisions.md` 생성

---

## Questions

아래 질문에 답변해주세요.

## Question 1
Spring Boot 빌드 도구는?

A) Gradle (권장 - 빌드 빠름, Spring Boot 기본)
B) Maven (전통적, XML 기반)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 2
Java 버전은?

A) Java 17 (LTS, Spring Boot 3.x 최소 요구)
B) Java 21 (최신 LTS, virtual thread 등 최신 기능)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 3
비밀번호 해싱 방식은?

A) BCrypt (Spring Security 기본, 가장 보편적)
B) SHA-256 + Salt (직접 구현, 단순)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

## Question 4
API 응답 시간 목표는?

A) 500ms 이내 (일반적인 웹 서비스 수준)
B) 200ms 이내 (빠른 응답 목표)
C) 특별한 목표 없음 (MVP이므로 동작만 하면 됨)
D) Other (please describe after [Answer]: tag below)

[Answer]: C

## Question 5
로깅 수준은?

A) 기본 로깅만 (Spring Boot 기본 설정, 에러/경고 위주)
B) 상세 로깅 (요청/응답 로깅, 비즈니스 이벤트 로깅 포함)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

# NFR Requirements - Unit 1: Backend

---

## 성능

| 항목 | 요구사항 |
|------|---------|
| API 응답 시간 | 특별한 목표 없음 (MVP - 동작 우선) |
| SSE 이벤트 전달 | 2초 이내 (requirements 기준) |
| 동시 사용자 | 소규모 (테이블 1~10개 + 관리자 1명) |
| DB 연결 풀 | Spring Boot 기본값 (HikariCP default) |

## 보안

| 항목 | 요구사항 |
|------|---------|
| 인증 | JWT 토큰 기반 |
| 비밀번호 | BCrypt 해싱 (Spring Security) |
| 관리자 API | role=ADMIN 검증 |
| 고객 API | role=TABLE + active 테이블 검증 |
| CORS | Frontend 도메인 허용 |

## 가용성/안정성

| 항목 | 요구사항 |
|------|---------|
| 배포 환경 | Docker Compose 로컬 (필수) |
| 장애 복구 | 수동 재시작 (MVP 수준) |
| 데이터 백업 | 미적용 (MVP) |
| SSE 재연결 | 클라이언트 자동 재연결 |

## 유지보수성

| 항목 | 요구사항 |
|------|---------|
| 로깅 | Spring Boot 기본 (에러/경고 위주) |
| 코드 구조 | 계층형 패키지 (controller/service/repository/entity) |
| API 문서 | 코드 내 주석 수준 (Swagger 미적용) |

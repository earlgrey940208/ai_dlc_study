# Tech Stack Decisions - Unit 1: Backend

---

## Core

| 기술 | 버전 | 선택 이유 |
|------|------|----------|
| Java | 17 (LTS) | Spring Boot 3.x 최소 요구, 안정적 |
| Spring Boot | 3.x (최신 stable) | 메인 프레임워크 |
| Gradle | Kotlin DSL | 빌드 도구, 빠른 빌드 |

## Dependencies

| 라이브러리 | 용도 |
|-----------|------|
| spring-boot-starter-web | REST API |
| spring-boot-starter-data-jpa | JPA/Hibernate ORM |
| spring-boot-starter-security | BCrypt, JWT 인증 필터 |
| spring-boot-starter-validation | 요청 유효성 검증 |
| jjwt (io.jsonwebtoken) | JWT 생성/검증 |
| postgresql | PostgreSQL JDBC 드라이버 |
| h2 | 로컬 개발/테스트용 in-memory DB |
| lombok | 보일러플레이트 코드 감소 |

## 설정 프로파일

| 프로파일 | DB | 용도 |
|---------|-----|------|
| default (dev) | H2 in-memory | 로컬 개발 |
| prod | PostgreSQL | Docker Compose 배포 |

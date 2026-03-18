# Infrastructure Design - Unit 1: Backend

---

## Docker Compose 서비스 구성

| 서비스 | 이미지 | 포트 | 설명 |
|--------|--------|------|------|
| frontend | Nginx (빌드된 React 정적 파일) | 80:80 | 고객/관리자 UI |
| backend | Spring Boot JAR | 8080:8080 | REST API + SSE |
| db | postgres:15 | 5432:5432 | PostgreSQL |

## 네트워크

- 단일 Docker 네트워크 (default)
- frontend → backend: `http://backend:8080` (컨테이너 내부 통신)
- backend → db: `jdbc:postgresql://db:5432/tableorder`
- 외부 접근: `http://localhost` (Frontend), `http://localhost:8080` (API)

## 볼륨

| 볼륨 | 마운트 | 용도 |
|------|--------|------|
| pgdata | /var/lib/postgresql/data | PostgreSQL 데이터 영속화 |

## 환경 변수

### backend
```
SPRING_PROFILES_ACTIVE=prod
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tableorder
SPRING_DATASOURCE_USERNAME=tableorder
SPRING_DATASOURCE_PASSWORD=tableorder
JWT_SECRET=<generated-secret>
```

### db
```
POSTGRES_DB=tableorder
POSTGRES_USER=tableorder
POSTGRES_PASSWORD=tableorder
```

## DB 초기화

- `docker/init.sql`: 스키마 + Seed data (카테고리, 메뉴, 관리자 계정)
- PostgreSQL 컨테이너의 `/docker-entrypoint-initdb.d/`에 마운트
- 최초 실행 시 자동 실행됨

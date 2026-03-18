# Deployment Architecture

---

## 로컬 개발 환경

```
개발자 PC
├── backend/  → ./gradlew bootRun (H2 in-memory, port 8080)
├── frontend/ → npm start (dev server, port 3000, proxy → 8080)
└── DB: H2 in-memory (자동)
```

- Backend: `./gradlew bootRun` (application-dev.yml, H2)
- Frontend: `npm start` (개발 서버, hot reload)
- DB 불필요 (H2 자동)

---

## Docker Compose 배포 (필수)

```
docker-compose up --build
```

```
+-------------------+     +-------------------+     +-------------------+
|    frontend       |     |    backend        |     |    db             |
|    (Nginx)        |---->|    (Spring Boot)  |---->|    (PostgreSQL)   |
|    :80            |     |    :8080          |     |    :5432          |
+-------------------+     +-------------------+     +-------------------+
         |                                                   |
    localhost:80                                         pgdata volume
```

### 빌드 순서
1. Frontend: `npm run build` → Nginx 이미지에 복사
2. Backend: `./gradlew bootJar` → JDK 이미지에 복사
3. DB: postgres:15 공식 이미지 + init.sql 마운트

### 실행
```bash
docker-compose up --build    # 빌드 + 실행
docker-compose down          # 중지 (데이터 유지)
docker-compose down -v       # 중지 + 데이터 삭제
```

---

## Rancher 배포 (선택)

- Docker Compose 기반으로 Rancher에 배포 시도
- 실패 시 로컬 Docker Compose로 대체
- 별도 Rancher 설정 파일은 Code Generation에서 필요 시 생성

# 요구사항 검증 질문

요구사항 문서(`requirements/table-order-requirements.md`)를 분석한 결과, 아래 항목들에 대한 추가 확인이 필요합니다.
각 질문의 `[Answer]:` 태그 뒤에 선택한 알파벳을 입력해 주세요.

---

## Question 1
기술 스택(Tech Stack)을 어떻게 결정하시겠습니까?

A) AI가 요구사항에 맞게 추천 (Python/FastAPI + React 등)
B) 직접 지정하겠습니다 (아래 Other에 기술)
C) Other (please describe after [Answer]: tag below)

[Answer]: B(React)

---

## Question 2
데이터베이스는 어떤 것을 사용하시겠습니까?

A) 관계형 DB (PostgreSQL)
B) 관계형 DB (MySQL)
C) NoSQL (MongoDB)
D) AI가 요구사항에 맞게 추천
E) Other (please describe after [Answer]: tag below)

[Answer]: A

---

## Question 3
배포 환경은 어디입니까?

A) AWS (클라우드)
B) 로컬 개발 환경만 (Docker Compose)
C) 온프레미스 서버
D) Other (please describe after [Answer]: tag below)

[Answer]: B

---

## Question 4
메뉴 이미지는 어떻게 관리합니까?

A) 외부 URL 입력 방식 (이미지 업로드 없음)
B) 서버에 직접 업로드 (파일 저장)
C) AWS S3 등 클라우드 스토리지 사용
D) Other (please describe after [Answer]: tag below)

[Answer]: A

---

## Question 5
실시간 주문 모니터링의 SSE(Server-Sent Events) 외에 추가 실시간 기능이 필요합니까?

A) SSE만으로 충분 (요구사항 문서 기준)
B) WebSocket도 필요 (양방향 실시간 통신)
C) Other (please describe after [Answer]: tag below)

[Answer]: A

---

## Question 6
MVP 개발 후 메뉴 관리 기능(CRUD)도 포함합니까?

A) 예, 메뉴 관리 기능도 MVP에 포함
B) 아니오, 메뉴는 초기 데이터(seed data)로만 처리
C) Other (please describe after [Answer]: tag below)

[Answer]: B

---

## Question 7
테이블 수는 대략 몇 개를 기준으로 설계합니까?

A) 소규모 (1~10개)
B) 중규모 (11~30개)
C) 대규모 (31개 이상)
D) Other (please describe after [Answer]: tag below)

[Answer]: A

---

답변 완료 후 "완료" 또는 "done"이라고 알려주세요.

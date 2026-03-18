# Code Summary - Unit 2: Frontend

## 생성된 파일 목록

### Project Setup
- `package.json` - 의존성 (React, Axios, React Router, Vite)
- `vite.config.js` - Vite 설정 (proxy → backend:8080)
- `index.html` - HTML 엔트리
- `src/main.jsx` - React 엔트리
- `src/App.jsx` - 라우터 설정
- `src/index.css` - 전역 스타일 (Noto Sans KR, 현대적 UI)
- `src/api/index.js` - Axios 인스턴스 (JWT 자동 첨부)

### Components
- `src/components/CustomerNav.jsx` - 고객용 하단 네비게이션

### Customer Pages
- `src/pages/customer/CustomerLogin.jsx` - 태블릿 인증 (US-1.1)
- `src/pages/customer/MenuPage.jsx` - 메뉴 조회 + 장바구니 담기 (US-2.1, US-3.1)
- `src/pages/customer/CartPage.jsx` - 장바구니 + 주문 생성 (US-3.1, US-3.2, US-4.1)
- `src/pages/customer/OrderHistoryPage.jsx` - 주문 내역 (US-4.2)

### Admin Pages
- `src/pages/admin/AdminLogin.jsx` - 관리자 로그인 (US-1.2)
- `src/pages/admin/Dashboard.jsx` - 실시간 대시보드 + SSE (US-5.1, US-5.2, US-6.2)
- `src/pages/admin/TableManagement.jsx` - 테이블 관리 (US-6.1, US-6.3, US-6.4)

## Story 커버리지
- 13개 Story 전부 Frontend 구현 완료

## UI 특징
- Noto Sans KR 폰트
- Apple 스타일 둥근 카드 UI
- 터치 친화적 (최소 44px 터치 영역)
- 반응형 그리드 레이아웃
- SSE 실시간 업데이트

-- 관리자 계정 (password: admin123, BCrypt 해시)
INSERT INTO admin (username, password_hash) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

-- 카테고리
INSERT INTO category (name, sort_order) VALUES
('추천 메뉴', 1),
('메인 요리', 2),
('사이드', 3),
('음료', 4);

-- 메뉴 (추천 메뉴)
INSERT INTO menu (category_id, name, price, description, image_url) VALUES
(1, '시그니처 스테이크', 28000, '부드러운 안심 스테이크와 구운 채소', 'https://images.unsplash.com/photo-1600891964092-4316c288032e?w=400&h=300&fit=crop'),
(1, '트러플 파스타', 18000, '블랙 트러플 오일의 크리미한 파스타', 'https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=400&h=300&fit=crop'),
(1, '연어 포케볼', 16000, '신선한 연어와 아보카도 포케', 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400&h=300&fit=crop');

-- 메뉴 (메인 요리)
INSERT INTO menu (category_id, name, price, description, image_url) VALUES
(2, '치킨 까르보나라', 15000, '바삭한 베이컨과 크리미한 소스', 'https://images.unsplash.com/photo-1612874742237-6526221588e3?w=400&h=300&fit=crop'),
(2, '마르게리타 피자', 14000, '모짜렐라와 바질의 클래식 피자', 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400&h=300&fit=crop'),
(2, '새우 리조또', 17000, '통새우와 파마산 치즈 리조또', 'https://images.unsplash.com/photo-1476124369491-e7addf5db371?w=400&h=300&fit=crop'),
(2, '불고기 덮밥', 12000, '달콤한 간장 양념 불고기와 밥', 'https://images.unsplash.com/photo-1553163147-622ab57be1c7?w=400&h=300&fit=crop');

-- 메뉴 (사이드)
INSERT INTO menu (category_id, name, price, description, image_url) VALUES
(3, '시저 샐러드', 9000, '로메인 상추와 파마산 드레싱', 'https://images.unsplash.com/photo-1550304943-4f24f54ddde9?w=400&h=300&fit=crop'),
(3, '감자튀김', 6000, '바삭한 감자튀김과 트러플 소금', 'https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=400&h=300&fit=crop'),
(3, '마늘빵', 5000, '버터 마늘빵 4조각', 'https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?w=400&h=300&fit=crop');

-- 메뉴 (음료)
INSERT INTO menu (category_id, name, price, description, image_url) VALUES
(4, '아메리카노', 4500, '깊은 풍미의 에스프레소', 'https://images.unsplash.com/photo-1521302080334-4bebac2763a6?w=400&h=300&fit=crop'),
(4, '레몬에이드', 5500, '상큼한 수제 레몬에이드', 'https://images.unsplash.com/photo-1621263764928-df1444c5e859?w=400&h=300&fit=crop'),
(4, '생맥주', 7000, '시원한 생맥주 500ml', 'https://images.unsplash.com/photo-1608270586620-248524c67de9?w=400&h=300&fit=crop');

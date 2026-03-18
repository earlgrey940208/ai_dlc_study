# Domain Entities - Unit 1: Backend

## Entity Relationship

```
Admin (독립)
Category 1──N Menu
TableInfo 1──N Order
TableInfo 1──N OrderHistory
Order 1──N OrderItem
OrderItem N──1 Menu
```

---

## Admin
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| username | String | unique, not null | |
| passwordHash | String | not null | 해싱된 비밀번호 |

## Category
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| name | String | not null | 카테고리명 |
| sortOrder | Integer | default 0 | 정렬 순서 |

## Menu
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| categoryId | Long (FK) | not null | Category 참조 |
| name | String | not null | 메뉴명 |
| price | Integer | not null | 가격 (원) |
| description | String | nullable | 설명 |
| imageUrl | String | nullable | 외부 이미지 URL |

## TableInfo
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| tableNumber | Integer | unique, not null | 테이블 번호 |
| passwordHash | String | not null | 태블릿 인증용 |
| active | Boolean | default false | 현재 사용 중 여부 |

## Order
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| tableId | Long (FK) | not null | TableInfo 참조 |
| status | String | not null | PENDING / PREPARING / COMPLETED |
| totalAmount | Integer | not null | 총 금액 |
| createdAt | LocalDateTime | not null | 주문 시각 |

## OrderItem
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| orderId | Long (FK) | not null | Order 참조 |
| menuId | Long (FK) | not null | Menu 참조 |
| menuName | String | not null | 주문 시점 메뉴명 (스냅샷) |
| price | Integer | not null | 주문 시점 가격 (스냅샷) |
| quantity | Integer | not null, min 1 | 수량 |

## OrderHistory
| Field | Type | 제약 | 설명 |
|-------|------|------|------|
| id | Long (PK) | auto | |
| tableId | Long (FK) | not null | TableInfo 참조 |
| orderData | String (TEXT) | not null | 주문 내역 JSON 스냅샷 |
| totalAmount | Integer | not null | 총 금액 |
| completedAt | LocalDateTime | not null | 이용 완료 시각 |

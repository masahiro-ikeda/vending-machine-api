# 支払記録
CREATE TABLE PAYMENT (
　　  PAYMENT_ID VARCHAR(36) NOT NULL,
　　  PAYMENT_AMOUNT INT NOT NULL,
　　  PAYMENT_TYPE VARCHAR(10) NOT NULL,
　　  PAYMENT_AT TIMESTAMP NOT NULL,
　　  PRIMARY KEY (PAYMENT_ID)
);

# 現金入出金
CREATE TABLE CASH_INOUT (
    CASH_INOUT_ID VARCHAR(36) NOT NULL,
    CASH_CURRENCY INT NOT NULL,
    CASH_INOUT_TYPE VARCHAR(10) NOT NULL,
    CASH_INOUT_QUANTITY INT NOT NULL,
    CASH_INOUT_AT TIMESTAMP NOT NULL,
    PRIMARY KEY (CASH_INOUT_ID)
);

# 現金入出金の初期データ
INSERT INTO CASH_INOUT (CASH_INOUT_ID, CASH_CURRENCY, CASH_INOUT_TYPE, CASH_INOUT_QUANTITY, CASH_INOUT_AT) VALUES
("934615bb-d0a7-c54a-9d14-8fe810bf8762", 10, "IN", 100, NOW()),
("db3666bf-d811-ffb5-1fba-078e1c3b2400", 50, "IN", 100, NOW()),
("b268ac09-4cd2-5c87-8dc4-5b59009c50f9", 100, "IN", 100, NOW()),
("5d9f16f2-87f7-d212-5a5a-261402acc253", 500, "IN", 100, NOW()),
("df56ab63-a0f5-875e-6dfa-45b10dd976ba", 1000, "IN", 100, NOW());

# ドリンクマスタ
CREATE TABLE DRINK (
    DRINK_ID VARCHAR(36) NOT NULL,
    DRINK_NAME VARCHAR(20) NOT NULL,
    DRINK_PRICE INT NOT NULL,
    PRIMARY KEY (DRINK_ID)
);

# ドリンクマスタの初期データ
INSERT INTO DRINK (DRINK_ID, DRINK_NAME, DRINK_PRICE) VALUES
("55d49fc7-8c76-4bcb-9357-568700dd0f7a", "コーヒー", 130);

# ドリンク入出庫
CREATE TABLE DRINK_INOUT (
    DRINK_INOUT_ID VARCHAR(36) NOT NULL,
    DRINK_ID VARCHAR(36) NOT NULL,
    DRINK_INOUT_TYPE VARCHAR(10) NOT NULL,
    DRINK_INOUT_QUANTITY INT NOT NULL,
    DRINK_INOUT_AT TIMESTAMP NOT NULL,
    PRIMARY KEY (DRINK_INOUT_ID)
);

# 入出庫の初期データ
INSERT INTO DRINK_INOUT (DRINK_INOUT_ID, DRINK_ID, DRINK_INOUT_TYPE, DRINK_INOUT_QUANTITY, DRINK_INOUT_AT)　VALUES
("1dda8adf-ddfa-4445-b854-a63467893762", "55d49fc7-8c76-4bcb-9357-568700dd0f7a", "IN", 10, "2021-07-29 15:46:39");

# 販売記録
CREATE TABLE DRINK_SOLD (
    SOLD_ID VARCHAR(36) NOT NULL,
    DRINK_ID VARCHAR(36) NOT NULL,
    SOLD_QUANTITY INT NOT NULL,
    SOLD_AT TIMESTAMP NOT NULL,
    PRIMARY KEY (SOLD_ID)
);

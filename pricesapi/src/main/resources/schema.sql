DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES
(
    BRAND_ID   INT,
    START_DATE TIMESTAMP,
    END_DATE   TIMESTAMP,
    PRICE_LIST INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT,
    PRIORITY   INT,
    PRICE      DECIMAL(10, 2),
    CURR       VARCHAR(3)
);
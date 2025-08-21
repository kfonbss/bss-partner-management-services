CREATE TABLE revenueshare (
      id            UUID NOT NULL PRIMARY KEY,
    revenueshareid      SERIAL ,
    sharename           VARCHAR(1000),
    anpshare            NUMERIC(5,2) NOT NULL DEFAULT 10.00,
    agpshare            NUMERIC(5,2) NOT NULL DEFAULT 7.00,
    mspshare            NUMERIC(5,2) NOT NULL DEFAULT 15.00,
    ispshare            NUMERIC(5,2) NOT NULL DEFAULT 30.00,
    dotshare            NUMERIC(10,2) NOT NULL DEFAULT 4.00,
    create_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date         TIMESTAMP,
    isactive BOOLEAN    NOT NULL DEFAULT TRUE
);
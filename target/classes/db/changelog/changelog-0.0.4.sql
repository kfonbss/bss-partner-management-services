CREATE TABLE security (
    subscriberid    BIGSERIAL PRIMARY KEY,
    username        VARCHAR(128) NOT NULL UNIQUE,
    password        VARCHAR(128) NOT NULL DEFAULT 'Railwire123',
    groupid         INTEGER NOT NULL DEFAULT 1,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP,
    isactive        INTEGER NOT NULL DEFAULT 1
);
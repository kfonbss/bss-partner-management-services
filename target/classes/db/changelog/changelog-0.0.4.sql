CREATE TABLE security (
      id            UUID NOT NULL PRIMARY KEY,
    subscriberid    BIGSERIAL ,
    username        VARCHAR(128) NOT NULL UNIQUE,
    password        VARCHAR(128) NOT NULL DEFAULT 'Railwire123',
    groupid         INTEGER NOT NULL DEFAULT 1,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP,
    isactive        INTEGER NOT NULL DEFAULT 1
);
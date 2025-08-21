CREATE TABLE partnergroup (
      id            UUID NOT NULL PRIMARY KEY,
    partnergroupid  SERIAL ,
    anp             BIGINT,
    agp             BIGINT,
    msp             BIGINT,
    isp             BIGINT,
    revenueshareid  INTEGER,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP,
    isactive        BOOLEAN NOT NULL DEFAULT TRUE
);
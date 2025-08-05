CREATE TABLE partnergroup (
    partnergroupid  SERIAL PRIMARY KEY,
    anp             BIGINT,
    agp             BIGINT,
    msp             BIGINT,
    isp             BIGINT,
    revenueshareid  INTEGER,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP,
    isactive        BOOLEAN NOT NULL DEFAULT TRUE
);
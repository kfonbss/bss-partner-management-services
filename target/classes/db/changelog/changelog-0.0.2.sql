CREATE TABLE partneraccount (
    partneraccountid    SERIAL PRIMARY KEY,
    partnerid           BIGINT NOT NULL UNIQUE,
    balance             DOUBLE PRECISION,
    lastupdate          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
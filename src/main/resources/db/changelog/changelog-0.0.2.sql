CREATE TABLE partneraccount (
       id               UUID NOT NULL PRIMARY KEY,
    partneraccountid    SERIAL ,
    partnerid           BIGINT NOT NULL UNIQUE,
    balance             DOUBLE PRECISION,
    lastupdate          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
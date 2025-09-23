
CREATE SEQUENCE partnerdetail_partnerid_seq;

ALTER TABLE partnerdetail
ALTER COLUMN partnerid SET DEFAULT nextval('partnerdetail_partnerid_seq');

SELECT setval('partnerdetail_partnerid_seq',
              COALESCE((SELECT MAX(partnerid) FROM partnerdetail), 1),
              true);

CREATE TABLE partneraccount (
       id               UUID NOT NULL PRIMARY KEY,
    partneraccountid    SERIAL ,
    partnerid           BIGINT NOT NULL UNIQUE,
    balance             DOUBLE PRECISION,
    lastupdate          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
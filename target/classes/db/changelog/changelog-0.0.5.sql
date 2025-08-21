CREATE TABLE package_map (
     id         UUID NOT NULL PRIMARY KEY,
    mapid       BIGSERIAL ,
    partnerid   BIGINT NOT NULL,
    packageid   INTEGER,
    status      BOOLEAN NOT NULL DEFAULT TRUE,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP,
    isactive    BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE block_details (
    block_details_id UUID NOT NULL PRIMARY KEY,
    id              BIGSERIAL,
    village_name    VARCHAR(250),
    block_id        INTEGER,
    block_name      VARCHAR(250),
    village_type    VARCHAR(50),
    village_type_id INTEGER,
    district        VARCHAR(100),
    district_id     INTEGER,
    loc_type        INTEGER,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active       BOOLEAN NOT NULL DEFAULT TRUE
);
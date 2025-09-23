CREATE TABLE pincode_details (
    pin_id            UUID NOT NULL PRIMARY KEY,
    id                  BIGSERIAL ,
    pincode             INTEGER,
    post_office_name    VARCHAR(250),
    sub_po_name         VARCHAR(250),
    district            VARCHAR(100),
    districtcode        INTEGER,
    create_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date         TIMESTAMP,
    isactive            BOOLEAN NOT NULL DEFAULT TRUE
);
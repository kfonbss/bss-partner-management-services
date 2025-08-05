CREATE TABLE bank_details (
    bank_id         BIGSERIAL PRIMARY KEY,
    bank_name       VARCHAR(100) NOT NULL,
    bank_ifsc_code  VARCHAR(100) NOT NULL,
    bank_micr       VARCHAR(100) NOT NULL,
    bank_branch     VARCHAR(100) NOT NULL,
    bank_address    VARCHAR(250) NOT NULL,
    bank_contact    BIGINT NOT NULL,
    bank_city       VARCHAR(150) NOT NULL,
    bank_district   VARCHAR(150) NOT NULL,
    bank_state      VARCHAR(150) NOT NULL,
    create_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date     TIMESTAMP,
    isactive        BOOLEAN NOT NULL DEFAULT TRUE
);
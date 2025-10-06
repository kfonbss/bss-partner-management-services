CREATE TABLE pop_master (
    pop_master_id UUID NOT NULL PRIMARY KEY,
    id          SERIAL,
    popname     VARCHAR(256),
    district    VARCHAR(100),
    poptype     VARCHAR(50),
    popid       VARCHAR(100),
    created_by  VARCHAR(100),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP,
    isactive    BOOLEAN NOT NULL DEFAULT TRUE
);
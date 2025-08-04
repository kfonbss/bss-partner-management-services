CREATE TABLE pop_master (
    id          SERIAL PRIMARY KEY,
    popname     VARCHAR(256),
    district    VARCHAR(100),
    poptype     VARCHAR(50),
    popid       VARCHAR(100),
    created_by  VARCHAR(100),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP,
    isactive    BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE sms_logs
(
    id                   uuid not null primary key,
    mobile               varchar(20),
    message              varchar(500),
    template_id          varchar(100),
    status_code          int,
    status_message       varchar(100),
    api_response         text,
    created_at           timestamp
)
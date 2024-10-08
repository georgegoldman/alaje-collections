CREATE TABLE countries (
    iso        VARCHAR(5),
    iso3       VARCHAR(5),
    currency   VARCHAR(6),
    num_code   VARCHAR(6),
    phone_code VARCHAR(6),
    created_at TIMESTAMP(6),
    id         BIGINT NOT NULL PRIMARY KEY,
    updated_at TIMESTAMP(6),
    name       VARCHAR(120),
    nick_name  VARCHAR(120),
    created_by VARCHAR(255),
    status     VARCHAR(255) CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED', 'PENDING', 'APPROVED', 'REJECTED', 'CANCELED', 'LOCKED')),
    updated_by VARCHAR(255)
);

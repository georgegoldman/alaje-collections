CREATE TABLE organizations (
    created_at  TIMESTAMP(6),
    id          BIGINT NOT NULL PRIMARY KEY,
    updated_at  TIMESTAMP(6),
    name        VARCHAR(200) NOT NULL UNIQUE,
    rc_number   VARCHAR(20),
    email       VARCHAR(200),
    telephone   VARCHAR(16),
    created_by  VARCHAR(255),
    status      VARCHAR(255) CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED', 'PENDING', 'APPROVED', 'REJECTED', 'CANCELED', 'LOCKED')),
    updated_by  VARCHAR(255)
);

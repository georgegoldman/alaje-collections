CREATE TABLE suspicious_activity_types (
    created_at TIMESTAMP(6),
    id         BIGINT NOT NULL PRIMARY KEY,
    updated_at TIMESTAMP(6),
    name       VARCHAR(100),
    created_by VARCHAR(255),
    status     VARCHAR(255) CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED', 'PENDING', 'APPROVED', 'REJECTED', 'CANCELED', 'LOCKED')),
    updated_by VARCHAR(255)
);

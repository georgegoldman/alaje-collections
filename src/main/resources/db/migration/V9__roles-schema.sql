CREATE TABLE roles (
    is_global      BOOLEAN,
    application_fk BIGINT,
    created_at     TIMESTAMP(6),
    id             BIGINT NOT NULL PRIMARY KEY,
    updated_at     TIMESTAMP(6),
    name           VARCHAR(100),
    description    VARCHAR(120),
    created_by     VARCHAR(255),
    status         VARCHAR(255) CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED', 'PENDING', 'APPROVED', 'REJECTED', 'CANCELED', 'LOCKED')),
    updated_by     VARCHAR(255),
    CONSTRAINT FK_tbl_organizations_tbl_roles_col_organization_id FOREIGN KEY (organization_fk) REFERENCES organizations(id)
);

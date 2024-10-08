CREATE TABLE role_privileges (
    privilege_id BIGINT NOT NULL,
    role_id      BIGINT NOT NULL,
    PRIMARY KEY (privilege_id, role_id),
    CONSTRAINT FK_tbl_privileges_tbl_role_privileges_col_privilege_id FOREIGN KEY (privilege_id) REFERENCES privileges(id),
    CONSTRAINT FK_tbl_roles_tbl_role_privileges_col_role_id FOREIGN KEY (role_id) REFERENCES roles(id)
);

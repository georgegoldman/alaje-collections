CREATE TABLE user_roles (
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, user_id),
    CONSTRAINT FK_tbl_roles_tbl_user_roles_col_role_id FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT FK_tbl_users_tbl_user_roles_col_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

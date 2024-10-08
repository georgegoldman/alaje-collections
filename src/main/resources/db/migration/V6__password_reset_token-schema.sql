CREATE TABLE password_reset_token (
    expiry_date TIMESTAMP(6),
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    token BLOB,
    CONSTRAINT UK_tbl_users_tbl_password_reset_token_col_user_id
        FOREIGN KEY (user_id) REFERENCES users(id)
);

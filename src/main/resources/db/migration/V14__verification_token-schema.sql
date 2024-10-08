CREATE TABLE verification_token (
    expiry_date TIMESTAMP(6),
    id          BIGINT NOT NULL PRIMARY KEY,
    user_id     BIGINT NOT NULL UNIQUE,
    token       TEXT,
    CONSTRAINT UK_tbl_users_tbl_verification_token_col_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);

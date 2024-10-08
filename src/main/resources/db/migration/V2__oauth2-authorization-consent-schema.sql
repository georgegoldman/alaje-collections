CREATE TABLE authorization_consent
(
    registered_client_id varchar(100) NOT NULL,
    principal_name       varchar(200) NOT NULL,
    authorities          blob         NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

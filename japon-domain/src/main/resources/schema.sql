CREATE SEQUENCE IF NOT EXISTS users_id_seq
    maxvalue 999999999;
COMMENT ON SEQUENCE users_id_seq IS 'ユーザーIDシーケンス';

CREATE TABLE IF NOT EXISTS users
(
    id                       NUMERIC      NOT NULL CONSTRAINT users_pk PRIMARY KEY,
    uid                      VARCHAR(50)  NOT NULL,
    family_name              VARCHAR(100) NOT NULL,
    given_name               VARCHAR(100) NOT NULL,
    nick_name                VARCHAR(100),
    user_image               VARCHAR(255),
    user_profile_image       VARCHAR(255),
    email                    VARCHAR(255) NOT NULL,
    tel                      VARCHAR(20),
    del_flg                  VARCHAR(1) default '0' NOT NULL,
    created_at               timestamp default CURRENT_TIMESTAMP,
    updated_at               timestamp default CURRENT_TIMESTAMP,
    updated_by               NUMERIC
    );

CREATE UNIQUE INDEX IF NOT EXISTS users_uid_uindex
    on users (uid);

CREATE DATABASE IF NOT EXISTS chat;

USE chat;

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 用户ID
                      username VARCHAR(255) NOT NULL UNIQUE, -- 用户名，唯一
                      password VARCHAR(255) NOT NULL         -- 加密后的密码
);

#create database quizeDB;
#use quizeDB;

drop table results;
drop table questions;
drop table users;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    pass TEXT,
    login TEXT
);
CREATE TABLE questions (
    id_question INT AUTO_INCREMENT PRIMARY KEY,
    category TEXT,
    question TEXT,
    ans_a TEXT,
    ans_b TEXT,
    ans_c TEXT,
    ans_d TEXT,
    answer TEXT
);
CREATE TABLE results (
    id_result INT AUTO_INCREMENT,
    id_user INT,
    result DOUBLE,
    PRIMARY KEY (id_result),
    FOREIGN KEY (id_user)
        REFERENCES users (id_user)
);


--liquibase formatted sql

--changeset bob:1

create table project_login.usuario
(
    codigo bigserial,
    nome VARCHAR (50) NOT NULL,
    email VARCHAR (50) NOT NULL,
    telefone VARCHAR (50) NOT NULL,
    senha VARCHAR (150) NOT NULL,
    primary key (codigo)
);

-- populando tabela usuario
insert into project_login.usuario values (default, 'erick', 'erickleda10@gmail.com', '993884719','$2a$10$odhsCQ/w59kE8TbvORQM6.ct7UxupItZE2JkjQv6m6AFwDJiRAY6e');
insert into project_login.usuario values (default, 'francisco', 'prjr@gmail.com', '984682186','$2a$10$odhsCQ/w59kE8TbvORQM6.ct7UxupItZE2JkjQv6m6AFwDJiRAY6e');
--liquibase formatted sql

--changeset bob:1

create table project_login.permissao
(
    codigo bigserial,
    descricao VARCHAR (50) NOT NULL,
    primary key (codigo)
);

INSERT INTO project_login.permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO project_login.permissao (codigo, descricao) values (2, 'ROLE_PESQUISAR_USUARIO');





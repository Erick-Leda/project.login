--liquibase formatted sql

--changeset bob:1

CREATE TABLE project_login.usuario(
    id bigserial,
    nome varchar (255) not null,
    email varchar (255) not null unique,
    telefone varchar (20) not null,
    primary key (id)

);

-- populando tabela usuario
insert into project_login.usuario values (default, 'erick', 'erickleda10@gmail.com', '993884719');
insert into project_login.usuario values (default, 'jorge', 'jorgeleda10@gmail.com', '993475656');
insert into project_login.usuario values (default, 'roberto', 'robertoleda10@gmail.com', '948494986');
insert into project_login.usuario values (default, 'francisco', 'prjr@gmail.com', '984682186');
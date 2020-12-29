--liquibase formatted sql

--changeset bob:1

create table project_login.usuario_permissao
(
    codigo_usuario bigserial references project_login.usuario (codigo) match simple on UPDATE cascade on DELETE cascade,
    codigo_permissao bigserial references project_login.permissao (codigo) match simple on UPDATE cascade on DELETE cascade
);

-- erick
INSERT INTO project_login.usuario_permissao (codigo_usuario, codigo_permissao)values (1, 1);
INSERT INTO project_login.usuario_permissao (codigo_usuario, codigo_permissao)values (1, 2);

-- francisco
INSERT INTO project_login.usuario_permissao (codigo_usuario, codigo_permissao)values (2, 2);






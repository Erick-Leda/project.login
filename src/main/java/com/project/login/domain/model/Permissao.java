package com.project.login.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao", schema = "project_login")
@Data

public class Permissao {

    @Id
    private Long codigo;

    private String descricao;

}
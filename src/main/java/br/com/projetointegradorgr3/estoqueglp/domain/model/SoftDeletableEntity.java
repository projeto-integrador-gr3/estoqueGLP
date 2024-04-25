package br.com.projetointegradorgr3.estoqueglp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeletableEntity {

    @Column(name = "deletado")
    private boolean deleted;
}

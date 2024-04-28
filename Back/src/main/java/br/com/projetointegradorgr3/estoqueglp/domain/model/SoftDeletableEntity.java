package br.com.projetointegradorgr3.estoqueglp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SoftDeletableEntity {

    @Column(name = "deletado")
    private boolean deleted;

    @Column(name = "deletado_em")
    private LocalDateTime deletedDate;
}

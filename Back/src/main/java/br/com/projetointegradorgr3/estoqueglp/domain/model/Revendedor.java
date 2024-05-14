package br.com.projetointegradorgr3.estoqueglp.domain.model;

import jakarta.persistence.*;

//@Entity
public class Revendedor extends SoftDeletableEntity {

  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String nome;
    private String telefone;

    //@OneToOne
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

package br.com.projetointegradorgr3.estoqueglp.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "controle_estoque")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "qtd_entrada")
    private int entradas;

    @Column(name = "qtd_vendas")
    private int vendas;

    @Column(name = "data_transacao")
    private LocalDateTime data;

    @Transient
    private int estoqueAposTransacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getEstoqueAposTransacao() {
        return estoqueAposTransacao;
    }

    public void setEstoqueAposTransacao(int estoqueAposTransacao) {
        this.estoqueAposTransacao = estoqueAposTransacao;
    }
}

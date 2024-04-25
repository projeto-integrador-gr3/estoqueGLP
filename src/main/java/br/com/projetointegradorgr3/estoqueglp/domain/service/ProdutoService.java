package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public Produto cadastrar(Produto produto) {
        return repository.save(produto);
    }

    public void atualizar(Produto produto) {
        repository.save(produto);
    }

    public Page<Produto> buscar(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Produto> buscar(Long id) {
        return repository.findById(id);
    }
}

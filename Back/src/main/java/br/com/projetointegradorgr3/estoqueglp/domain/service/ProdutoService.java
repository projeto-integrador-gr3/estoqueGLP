package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Fornecedor;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.FornecedorRepository;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final FornecedorRepository fornecedorRepository;
    private static final String RECURSO = "produto";

    public ProdutoService(ProdutoRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public Produto cadastrar(Produto produto) {

        produto = repository.save(produto);
        Fornecedor fornecedor = fornecedorRepository.findById(produto.getFornecedor().getId()).orElse(null);
        produto.setFornecedor(fornecedor);

        return produto;
    }

    @Transactional
    public void atualizar(Produto produto) {
        buscar(produto.getId());
        repository.save(produto);

        Fornecedor fornecedor = fornecedorRepository.findById(produto.getFornecedor().getId()).orElse(null);
        produto.setFornecedor(fornecedor);
    }

    public List<Produto> buscar() {
        return repository.findAll();
    }

    public Produto buscar(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(RECURSO, id));
    }
}

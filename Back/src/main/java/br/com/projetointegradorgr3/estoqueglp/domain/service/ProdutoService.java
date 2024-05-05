package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UnprocessableEntityException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final UsuarioService usuarioService;
    private final RevendedorService revendedorService;
    private static final String RECURSO = "produto";

    public ProdutoService(ProdutoRepository repository, UsuarioService usuarioService, RevendedorService revendedorService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.revendedorService = revendedorService;
    }

    public Produto cadastrar(Produto produto) {
        String usuarioLogado = usuarioService.usuarioLogado();
        Revendedor revendedor = revendedorService.buscarPorUsername(usuarioLogado);
        produto.setRevendedor(revendedor);
        produto.setEstoque(0);

        return repository.save(produto);
    }

    public void atualizar(Produto produto) {
        if (buscar(produto.getId()).isEmpty()) {
            throw new NotFoundException(RECURSO);
        }
        repository.save(produto);
    }

    public Page<Produto> buscar(Pageable pageable) {
        String usuarioLogado = usuarioService.usuarioLogado();
        return repository.findAllByDeletedIsFalseAndRevendedorUsuarioUsername(pageable, usuarioLogado);
    }

    public void deletar(Long id) {
        Produto produto = buscar(id).orElseThrow(() -> new NotFoundException(RECURSO));
        repository.deleteById(produto.getId());
    }

    public Optional<Produto> buscar(Long id) {
        String usuarioLogado = usuarioService.usuarioLogado();
        return repository.findById(id).filter(produto -> usuarioLogado.equals(produto.getRevendedor().getUsuario().getUsername()));
    }

    public Produto atualizarEstoque(Long id, int quantidade) {
        Produto produto = buscar(id).orElseThrow(() -> new NotFoundException(RECURSO));
        if (produto.getEstoque() + quantidade < 0) {
            throw new UnprocessableEntityException("Estoque não pode ficar vazio");
        }

        produto.setEstoque(produto.getEstoque() + quantidade);
        return repository.save(produto);
    }
}

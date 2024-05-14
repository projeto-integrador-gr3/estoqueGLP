package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final UsuarioService usuarioService;
    private static final String RECURSO = "produto";

    public ProdutoService(ProdutoRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public Produto cadastrar(Produto produto) {
        String usuarioLogado = usuarioService.usuarioLogado();
        /*Revendedor revendedor = revendedorService.buscarPorUsername(usuarioLogado);
        produto.setRevendedor(revendedor);
        produto.setEstoque(0);*/

        return repository.save(produto);
    }

    public void atualizar(Produto produto) {
        if (buscar(produto.getId()).isEmpty()) {
            throw new NotFoundException(RECURSO);
        }
        repository.save(produto);
    }

    public List<Produto> buscar() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        Produto produto = buscar(id).orElseThrow(() -> new NotFoundException(RECURSO));
        repository.deleteById(produto.getId());
    }

    public Optional<Produto> buscar(Integer id) {
        //String usuarioLogado = usuarioService.usuarioLogado();
        return repository.findById(id);
    }

    public Produto atualizarEstoque(Integer id, int quantidade) {
        Produto produto = buscar(id).orElseThrow(() -> new NotFoundException(RECURSO));
        /*if (produto.getEstoque() + quantidade < 0) {
            throw new UnprocessableEntityException("Estoque não pode ficar vazio");
        }

        produto.setEstoque(produto.getEstoque() + quantidade);*/
        return repository.save(produto);
    }
}

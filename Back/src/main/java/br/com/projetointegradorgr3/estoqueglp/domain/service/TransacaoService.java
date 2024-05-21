package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.NotFoundException;
import br.com.projetointegradorgr3.estoqueglp.domain.exception.UnprocessableEntityException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Transacao;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private static final String RECURSO = "Transação";

    public TransacaoService(TransacaoRepository repository, UsuarioService usuarioService, ProdutoService produtoService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
    }

    public Transacao cadastrar(Transacao transacao) {
        String username = usuarioService.usuarioLogado();
        Usuario usuario = usuarioService.loadUserByUsername(username);
        transacao.setUsuario(usuario);
        transacao.setData(LocalDateTime.now());

        Produto produto = produtoService.buscar(transacao.getProduto().getId());
        transacao.setProduto(produto);

        List<Transacao> transacoesAnteriores = buscar(transacao.getProduto().getId());

        int estoqueAnterior = 0;

        if (!transacoesAnteriores.isEmpty()) {
            estoqueAnterior = transacoesAnteriores.getLast().getEstoqueAposTransacao();
        }

        transacao.setEstoqueAposTransacao(estoqueAnterior + transacao.calcularValorDaTransacao());

        if (transacao.getEstoqueAposTransacao() < 0) {
            throw new UnprocessableEntityException("Valor do estoque não pode ser negativo");
        }


        transacao = repository.save(transacao);

        transacao.getProduto().setQuantidadeEstoque(transacao.getEstoqueAposTransacao());
        return transacao;
    }

    public Transacao buscarPorId(Integer id) {
        String username = usuarioService.usuarioLogado();
        Transacao transacaoSemCalculo = repository.findByIdAndUsuarioUsername(id, username).orElseThrow(() -> new NotFoundException(RECURSO, id));
        List<Transacao> transacoes = buscar(transacaoSemCalculo.getProduto().getId());

        return transacoes.stream().filter(transacao -> transacao.getId().equals(transacaoSemCalculo.getId())).findAny().orElseThrow(() -> new IllegalStateException("Problemas ao calcular estoque"));
    }

    public List<Transacao> buscar(Integer produtoId) {
        String username = usuarioService.usuarioLogado();
        List<Transacao> transacoes;
        if (produtoId != null) {
            transacoes = repository.findAllByProdutoIdAndUsuarioUsername(produtoId, username);
        } else {
            transacoes = repository.findAllByUsuarioUsername(username);
        }
        preencherQuantidadeEmEstoqueAposOperacao(transacoes);
        return transacoes;
    }

    public int buscarQuantidadeEmEstoque(Integer produtoId) {
        List<Transacao> transacoes = buscar(produtoId);
        if (transacoes.isEmpty()) {
            return 0;
        }

        return transacoes.getLast().getEstoqueAposTransacao();
    }

    private void preencherQuantidadeEmEstoqueAposOperacao(List<Transacao> transacoes) {
        Map<Produto, List<Transacao>> transacoesAgrupadas = transacoes.stream()
                .collect(Collectors.groupingBy(Transacao::getProduto));

        for (Map.Entry<Produto, List<Transacao>> entrySet : transacoesAgrupadas.entrySet()) {
            entrySet.getValue().sort(Comparator.comparing(Transacao::getData));

            int estoque = 0;
            for (Transacao transacao : entrySet.getValue()) {
                estoque += transacao.calcularValorDaTransacao();
                transacao.setEstoqueAposTransacao(estoque);
            }

            entrySet.getKey().setQuantidadeEstoque(estoque);
        }
    }
}

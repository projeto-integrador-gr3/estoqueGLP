package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.ProdutoDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.service.ProdutoService;
import br.com.projetointegradorgr3.estoqueglp.domain.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos")
@SecurityRequirement(name = "basicAuth")
public class ProdutoController {

    private final ProdutoService service;
    private final TransacaoService transacaoService;

    public ProdutoController(ProdutoService service, TransacaoService transacaoService) {
        this.service = service;
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo produto")
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {

        Produto produto = service.cadastrar(produtoDto.converter());

        return ResponseEntity
                .created(uriBuilder.path("/produtos/{id}")
                        .buildAndExpand(produto.getId())
                        .toUri())
                .body(new ProdutoDto(produto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar informações do produto")
    public ResponseEntity<ProdutoDto> atualizar(@RequestBody @Valid ProdutoDto produtoDto, @PathVariable("id") Integer id) {
        Produto produto = produtoDto.converter();
        produto.setId(id);
        service.atualizar(produto);

        int quantidadeEmEstoque = transacaoService.buscarQuantidadeEmEstoque(produto.getId());

        return ResponseEntity.ok(new ProdutoDto(produto, quantidadeEmEstoque));
    }

    @GetMapping
    @Operation(summary = "Buscar todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoDto>> buscarTodos() {
        List<Produto> produtos = service.buscar();

        List<ProdutoDto> produtosDto = produtos.stream().map(produto -> new ProdutoDto(produto, transacaoService.buscarQuantidadeEmEstoque(produto.getId()))).toList();

        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto especifico por id")
    public ResponseEntity<ProdutoDto> buscar(@PathVariable("id") Integer id) {
        Produto produto = service.buscar(id);

        return ResponseEntity.ok(new ProdutoDto(produto, transacaoService.buscarQuantidadeEmEstoque(produto.getId())));
    }
}

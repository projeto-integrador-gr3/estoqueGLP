package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.ProdutoDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import br.com.projetointegradorgr3.estoqueglp.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {

        Produto produto = service.cadastrar(produtoDto.converter());

        return ResponseEntity
                .created(uriBuilder.path("/produtos/{}")
                        .buildAndExpand(produto.getId())
                        .toUri())
                .body(new ProdutoDto(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@RequestBody @Valid ProdutoDto produtoDto, @PathVariable("id") Long id) {
        Produto produto = produtoDto.converter();
        produto.setId(id);
        service.atualizar(produto);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> buscarTodos(Pageable pageable) {
        Page<Produto> produtos = service.buscar(pageable);

        Page<ProdutoDto> produtosDto = produtos.map(ProdutoDto::new);

        return ResponseEntity.ok(produtosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscar(@PathVariable("id") Long id) {

        return service.buscar(id).map(produto -> ResponseEntity.ok(new ProdutoDto(produto))).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}

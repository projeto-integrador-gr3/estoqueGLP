package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.EstoqueDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Transacao;
import br.com.projetointegradorgr3.estoqueglp.domain.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/estoques")
@Tag(name = "Estoques")
@SecurityRequirement(name = "basicAuth")
public class EstoqueController {

    private final TransacaoService transacaoService;

    public EstoqueController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar nova transação de estoque")
    public ResponseEntity<EstoqueDto> cadastrar(@RequestBody @Valid EstoqueDto estoqueDto, UriComponentsBuilder uriBuilder) {

        Transacao transacao = transacaoService.cadastrar(estoqueDto.converter());

        return ResponseEntity.created(uriBuilder.path("/estoques/{id}")
                        .buildAndExpand(transacao.getId())
                        .toUri())
                .body(new EstoqueDto(transacao));
    }

    @GetMapping
    @Operation(summary = "Buscar transações de estoque",
            description = "Buscar todas as transações do usuário, pode ser filtrado pelo id do produto")
    public ResponseEntity<List<EstoqueDto>> buscar(@RequestParam(value = "produto", required = false) Integer produtoId) {
        List<Transacao> transacoes = transacaoService.buscar(produtoId);
        List<EstoqueDto> dtos = transacoes.stream().sorted(Comparator.comparing(Transacao::getData).reversed()).map(EstoqueDto::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar transação por id",
            description = "Buscar transação do usuário pelo id")
    public ResponseEntity<EstoqueDto> buscarPorId(@PathVariable("id") Integer id) {
        Transacao transacao = transacaoService.buscarPorId(id);

        return ResponseEntity.ok(new EstoqueDto(transacao));
    }
}

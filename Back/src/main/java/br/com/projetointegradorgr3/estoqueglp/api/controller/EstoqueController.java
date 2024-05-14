package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.EstoqueDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Transacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/estoques")
@Tag(name = "Estoques")
@SecurityRequirement(name = "basicAuth")
public class EstoqueController {

    @PostMapping
    @Operation(summary = "Cadastrar nova transação de estoque")
    public ResponseEntity<EstoqueDto> cadastrar(@RequestBody @Valid EstoqueDto estoqueDto, UriComponentsBuilder uriBuilder) {

        return ResponseEntity.created(uriBuilder.path("/estoques/{id}")
                        .buildAndExpand(estoqueDto.id())
                        .toUri())
                .body(estoqueDto);
    }

    @GetMapping
    @Operation(summary = "Buscar transações de estoque",
            description = "Buscar todas as transações do usuário, pode ser filtrado pelo id do produto")
    public ResponseEntity<List<EstoqueDto>> buscar(@RequestParam(value = "produto", required = false) Integer produtoId) {

        return ResponseEntity.ok(List.of(new EstoqueDto(new Transacao())));
    }
}

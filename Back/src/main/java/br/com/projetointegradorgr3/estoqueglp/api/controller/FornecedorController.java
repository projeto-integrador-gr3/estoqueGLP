package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.FornecedorDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Fornecedor;
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
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedores")
@SecurityRequirement(name = "basicAuth")
public class FornecedorController {

    @PostMapping
    @Operation(summary = "Cadastrar novo fornecedor")
    public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorDto fornecedorDto, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.created(uriBuilder.path("/fornecedores/{id}")
                        .buildAndExpand(fornecedorDto.id())
                        .toUri())
                .body(fornecedorDto);

    }

    @GetMapping
    @Operation(summary = "buscar todos os fornecedores cadastrados")
    public ResponseEntity<List<FornecedorDto>> buscarTodos() {
        return ResponseEntity.ok(List.of(new FornecedorDto(new Fornecedor())));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar fornecedor pelo id")
    public ResponseEntity<FornecedorDto> buscar(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new FornecedorDto(new Fornecedor()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar informações do fornecedor")
    public ResponseEntity<FornecedorDto> alterar(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new FornecedorDto(new Fornecedor()));
    }
}

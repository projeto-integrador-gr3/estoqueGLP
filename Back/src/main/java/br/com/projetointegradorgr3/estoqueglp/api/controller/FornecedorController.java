package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.FornecedorDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Fornecedor;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.FornecedorRepository;
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

    private final FornecedorRepository repository;

    public FornecedorController(FornecedorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo fornecedor")
    public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorDto fornecedorDto, UriComponentsBuilder uriBuilder) {
        Fornecedor fornecedor = repository.save(fornecedorDto.converter());

        return ResponseEntity.created(uriBuilder.path("/fornecedores/{id}")
                        .buildAndExpand(fornecedor.getId())
                        .toUri())
                .body(new FornecedorDto(fornecedor));

    }

    @GetMapping
    @Operation(summary = "buscar todos os fornecedores cadastrados")
    public ResponseEntity<List<FornecedorDto>> buscarTodos() {
        List<FornecedorDto> fornecedores = repository.findAll().stream().map(FornecedorDto::new).toList();

        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar fornecedor pelo id")
    public ResponseEntity<FornecedorDto> buscar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(fornecedor -> ResponseEntity.ok(new FornecedorDto(fornecedor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar informações do fornecedor")
    public ResponseEntity<FornecedorDto> alterar(@PathVariable("id") Integer id, @RequestBody FornecedorDto fornecedorDto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Fornecedor fornecedor = fornecedorDto.converter();
        fornecedor.setId(id);

        fornecedor = repository.save(fornecedor);

        return ResponseEntity.ok(new FornecedorDto(fornecedor));
    }
}

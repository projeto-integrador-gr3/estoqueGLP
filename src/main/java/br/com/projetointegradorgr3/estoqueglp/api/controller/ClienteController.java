package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.ClienteDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Cliente;
import br.com.projetointegradorgr3.estoqueglp.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastrar(@Valid @RequestBody ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
        Cliente cliente = service.cadastrar(clienteDto.toModel());
        return ResponseEntity.created(uriBuilder.path("/clientes/{id}")
                        .buildAndExpand(cliente.getId())
                        .toUri())
                .body(new ClienteDto(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCadastro(@PathVariable("id") Long id, @Valid @RequestBody ClienteDto clienteDto) {
        Cliente cliente = clienteDto.toModel();
        cliente.setId(id);
        service.atualizar(cliente);

        return ResponseEntity.ok(new ClienteDto(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscar(@PathVariable("id") Long id){
        return service.buscar(id).map(cliente -> ResponseEntity.ok(new ClienteDto(cliente))).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDto>> buscarTodos(Pageable pageable) {
        Page<Cliente> clientes = service.buscar(pageable);

        List<ClienteDto> clientesDto = clientes.stream()
                .map(ClienteDto::new)
                .toList();

        return ResponseEntity.ok(new PageImpl<>(clientesDto));
    }
}

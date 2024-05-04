package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.RevendedorDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import br.com.projetointegradorgr3.estoqueglp.domain.service.RevendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public class RevendedorController {

    private final RevendedorService revendedorService;

    public RevendedorController(RevendedorService revendedorService) {
        this.revendedorService = revendedorService;
    }

    public ResponseEntity<RevendedorDto> cadastrar(RevendedorDto revendedorDto, UriComponentsBuilder uriComponentsBuilder) {
        Revendedor revendedor = revendedorService.cadastrar(revendedorDto.converter());

        return ResponseEntity.created(uriComponentsBuilder.path("/revendedores/{id}")
                .buildAndExpand(revendedor.getId())
                .toUri())
                .body(new RevendedorDto(revendedor));
    }
}

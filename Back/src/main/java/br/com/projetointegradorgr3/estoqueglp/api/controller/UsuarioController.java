package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.LoginDto;
import br.com.projetointegradorgr3.estoqueglp.api.dto.TokenDto;
import br.com.projetointegradorgr3.estoqueglp.api.dto.UsuarioDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import br.com.projetointegradorgr3.estoqueglp.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;

@RestController
@Tag(name = "Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    public UsuarioController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/usuarios")
    @Operation(summary = "Cadastrar novo usuário")
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.cadastrar(usuarioDto.converter());

        return ResponseEntity.created(uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri())
                .body(new UsuarioDto(usuario));
    }

    @PostMapping("/login")
    @Operation(description = "Realizar login na plataforma")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha()));
            boolean isAdmin = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_ADMIN"::equals);

            String token = Base64.getEncoder().encodeToString((loginDto.login() + ":" + loginDto.senha()).getBytes());

            return ResponseEntity.ok(new TokenDto("Basic " + token, isAdmin));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }
}

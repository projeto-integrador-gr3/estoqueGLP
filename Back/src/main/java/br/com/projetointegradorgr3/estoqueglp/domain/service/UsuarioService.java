package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.UsuarioExistenteException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário \"" + username + "\" não existe"));
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new UsuarioExistenteException(usuario.getUsername());
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole("ROLE_DEFAULT");

        return repository.save(usuario);
    }

    public String usuarioLogado() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

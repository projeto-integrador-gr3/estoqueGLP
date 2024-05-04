package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.RevendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RevendedorService {

    private final RevendedorRepository repository;
    private final UsuarioService usuarioService;

    public RevendedorService(RevendedorRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public Revendedor cadastrar(Revendedor revendedor) {
        usuarioService.cadastrar(revendedor.getUsuario());
        return repository.save(revendedor);
    }
}

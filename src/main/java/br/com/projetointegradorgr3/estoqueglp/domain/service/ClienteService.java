package br.com.projetointegradorgr3.estoqueglp.domain.service;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Cliente;
import br.com.projetointegradorgr3.estoqueglp.domain.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(Cliente cliente) {

        return repository.save(cliente);
    }

    public void atualizar(Cliente cliente) {

        repository.save(cliente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Page<Cliente> buscar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Cliente> buscar(Long id) {
        return repository.findById(id);
    }
}

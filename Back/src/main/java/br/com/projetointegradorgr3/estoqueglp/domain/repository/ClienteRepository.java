package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends SoftDeletableRepository<Cliente, Long> {
}

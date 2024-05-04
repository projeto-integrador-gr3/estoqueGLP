package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import org.springframework.stereotype.Repository;

@Repository
public interface RevendedorRepository extends SoftDeletableRepository<Revendedor, Long> {
}

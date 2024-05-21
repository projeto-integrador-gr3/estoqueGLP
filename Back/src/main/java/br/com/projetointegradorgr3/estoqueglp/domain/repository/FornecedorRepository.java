package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}

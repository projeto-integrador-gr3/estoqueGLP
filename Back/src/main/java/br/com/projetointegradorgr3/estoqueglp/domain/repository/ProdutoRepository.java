package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends SoftDeletableRepository<Produto, Long> {

    Page<Produto> findAllByDeletedIsFalseAndRevendedorUsuarioUsername(Pageable pageable, String username);
}

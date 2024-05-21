package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findAllByProdutoIdAndUsuarioUsername(Integer produtoId, String username);

    List<Transacao> findAllByUsuarioUsername(String username);

    Optional<Transacao> findByIdAndUsuarioUsername(Integer id, String username);
}

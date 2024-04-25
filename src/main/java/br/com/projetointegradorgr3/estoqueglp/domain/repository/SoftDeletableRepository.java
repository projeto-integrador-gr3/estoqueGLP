package br.com.projetointegradorgr3.estoqueglp.domain.repository;

import br.com.projetointegradorgr3.estoqueglp.domain.model.SoftDeletableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeletableRepository<T extends SoftDeletableEntity, ID> extends JpaRepository<T, ID> {

    @Override
    @Modifying
    @Query("update #{#entityName} e set e.deleted = true, e.deletedDate = current_timestamp where e.id = ?1 and e.deleted = false")
    void deleteById(ID id);

    Page<T> findAllByDeletedIsFalse(Pageable pageable);
}

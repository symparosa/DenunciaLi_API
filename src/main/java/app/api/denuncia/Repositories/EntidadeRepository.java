package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.EntidadeModel;
@Repository
@Transactional
public interface EntidadeRepository extends JpaRepository<EntidadeModel, Integer>{
    
    Boolean existsByNome(String nome);

    List<EntidadeModel> findByEstadoIn(List<Integer> estados);

    Boolean  existsByNomeAndIdNot(String nome, Integer id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_entidade SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);
}

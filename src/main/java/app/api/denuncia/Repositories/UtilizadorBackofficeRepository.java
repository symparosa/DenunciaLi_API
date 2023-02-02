package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.UtilizadorBackofficeModel;

@Repository
@Transactional
public interface UtilizadorBackofficeRepository extends JpaRepository<UtilizadorBackofficeModel, Integer>{
    
    Boolean existsByUsername(String username);

    List<UtilizadorBackofficeModel> findByEstadoIn(List<Integer> estados);

    Boolean existsByUsernameAndIdNot(String username, int id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);
}

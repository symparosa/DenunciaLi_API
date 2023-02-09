package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.UtilizadorModel;

@Repository
@Transactional
public interface UtilizadorRepository extends JpaRepository<UtilizadorModel, Integer>{
    
    Boolean existsByUsername(String username);

    UtilizadorModel findByUsername(String username);

    List<UtilizadorModel> findByEstadoIn(List<Integer> estados);

    Boolean existsByUsernameAndIdNot(String username, int id);

    Boolean existsByUsernameAndHash(String username, String hush);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE() ,password =:password, hash =:hash, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer changePassword(@Param("password") String password, @Param("hash") String hash, @Param("user") int user, @Param("id") int id);
}

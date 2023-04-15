package app.api.denuncia.Repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DenuncianteModel;

@Repository
@Transactional
public interface DenuncianteRepository extends JpaRepository<DenuncianteModel, Integer> {

    Boolean existsByIdAndEstado(int id, int estado);

    Optional<DenuncianteModel> findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_denunciante SET token=:token, token_iat=:token_iat, data_atualizacao = GETDATE(),last_user_change=:id WHERE username =:username", nativeQuery = true)
    Optional<Integer> insertToken(@Param("token") String token, @Param("token_iat") LocalDateTime token_iat, @Param("id") int id,
            @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE(), token = null, token_iat = null, last_user_change=:id WHERE id =:id", nativeQuery = true)
    Integer logout(@Param("id") int id);
}

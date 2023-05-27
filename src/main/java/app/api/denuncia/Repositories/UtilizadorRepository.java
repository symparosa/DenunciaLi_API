package app.api.denuncia.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Statistic.Utilizador.UtilizadorPorEntidade;
import app.api.denuncia.Statistic.Utilizador.UtilizadorPorTipoUtilizador;
import app.api.denuncia.Statistic.Utilizador.UtilizadorTotal;

@Repository
@Transactional
public interface UtilizadorRepository extends JpaRepository<UtilizadorModel, Integer> {

        Boolean existsByHash(String hash);

        Boolean existsByUsername(String username);

        Boolean existsByIdAndEstado(int id, int estado);

        Boolean existsByUsernameAndIdNot(String username, int id);

        Optional<UtilizadorModel> findByUsername(String username);

        List<UtilizadorModel> findByEstadoIn(List<Integer> estados);

        Boolean existsByUsernameAndHash(String username, String hash);

        Optional<UtilizadorModel> findByUsernameAndEstado(String username, int estado);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE(),token=:token, token_iat=:token_iat, last_user_change=:id WHERE username =:username", nativeQuery = true)
        Optional<Integer> insertToken(@Param("token") String token, @Param("token_iat") LocalDateTime token_iat,
                        @Param("id") int id,
                        @Param("username") String username);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE(), token_iat=:token_iat, last_user_change=:id WHERE username =:username", nativeQuery = true)
        Optional<Integer> updateDateToken(@Param("token_iat") LocalDateTime token_iat, @Param("id") int id,
                        @Param("username") String username);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
        Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE() ,password =:password, hash = null, conta_confirmada=1, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer changePassword(@Param("password") String password, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE(), hash =:hash, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer updateHash(@Param("hash") String hash, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_utilizador_backoffice SET data_atualizacao = GETDATE(), token = null, token_iat = null, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer logout(@Param("id") int id);

        // -------------------------------------------------------------------------
        // ESTATÍSTICA
        // -------------------------------------------------------------------------

        @Query(value = "SELECT TotalUtilizadores,TotalAtivos,TotalInativos,TotalEliminados FROM dbo.view_por_utilizador", nativeQuery = true)
        List<UtilizadorTotal> getEstatisticaUtilizadorTotal();

        @Query(value = "SELECT Sigla,Nome,UtilizadorTotal FROM dbo.view_por_utilizador_entidade", nativeQuery = true)
        List<UtilizadorPorEntidade> getEstatisticaUtilizadorPorEntidade();

        @Query(value = "SELECT TipoUtilizador ,UtilizadoresTotal FROM view_por_utilizador_tipoUtilizador", nativeQuery = true)
        List<UtilizadorPorTipoUtilizador> getEstatisticaUtilizadorPorTipoUtilizador();
}

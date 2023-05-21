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

import app.api.denuncia.Models.DenuncianteModel;

@Repository
@Transactional
public interface DenuncianteRepository extends JpaRepository<DenuncianteModel, Integer> {

        Boolean existsByHash(String hash);

        Boolean existsByUsername(String username);

        Boolean existsByIdAndEstado(int id, int estado);

        Optional<DenuncianteModel> findByUsername(String username);

        List<DenuncianteModel> findByEstadoIn(List<Integer> estados);

        Boolean existsByUsernameAndHash(String username, String hash);

        Boolean existsByDocIdentificacaoAndUsernameNot(String doc, String username);

        Optional<DenuncianteModel> findByUsernameAndEstado(String username, int estado);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE(), hash =:hash, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer updateHash(@Param("hash") String hash, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer alterarEstado(@Param("estado") int estado, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE(), token = null, token_iat = null, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer logout(@Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE(), token_iat=:token_iat, last_user_change=:id WHERE username =:username", nativeQuery = true)
        Optional<Integer> updateDateToken(@Param("token_iat") LocalDateTime token_iat, @Param("id") int id,
                        @Param("username") String username);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET token=:token, token_iat=:token_iat, data_atualizacao = GETDATE(),last_user_change=:id WHERE username =:username", nativeQuery = true)
        Optional<Integer> insertToken(@Param("token") String token, @Param("token_iat") LocalDateTime token_iat,
                        @Param("id") int id,
                        @Param("username") String username);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET data_atualizacao = GETDATE() ,password =:password, hash = null, conta_confirmada=1, last_user_change=:id WHERE id =:id", nativeQuery = true)
        Integer changePassword(@Param("password") String password, @Param("id") int id);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_denunciante SET apelido =:apelido, codigo_postal =:codigo, data_atualizacao = GETDATE(), data_nascimento =:data, doc_identificacao =:doc, foto_perfil =:foto, genero =:genero, localizacao_mapa =:localizacao_mapa, nome =:nome, referencia_morada =:referencia, localizacao_fk =:localizacao_fk, last_user_change =:id WHERE username =:username", nativeQuery = true)
        Integer updateInfo(@Param("apelido") String apelido, @Param("codigo") String codigo,
                        @Param("data") LocalDateTime data, @Param("doc") String doc, @Param("foto") String foto,
                        @Param("genero") String genero, @Param("localizacao_mapa") String localizacao_mapa,
                        @Param("nome") String nome, @Param("referencia") String referencia,
                        @Param("localizacao_fk") String localizacao_fk, @Param("id") int id,
                        @Param("username") String username);
}

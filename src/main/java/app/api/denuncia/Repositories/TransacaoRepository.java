package app.api.denuncia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.TransacaoModel;

@Repository
@Transactional
public interface TransacaoRepository extends JpaRepository<TransacaoModel, Integer> {

    Boolean existsByBotaoAndTipoUtilizador(BotaoModel botao, DominioModel perfil);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_transacao SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE botao_fk =:id_b and tipo_utilizador_fk=:id_p", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id_b") int id_b,
            @Param("id_p") int id_p);
}

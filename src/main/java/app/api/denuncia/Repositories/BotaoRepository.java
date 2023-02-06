package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.BotaoDto;
import app.api.denuncia.Models.BotaoModel;

@Repository
@Transactional
public interface BotaoRepository extends JpaRepository<BotaoModel, Integer>{
    
    Boolean existsByCodigo(String codigo);

    Boolean  existsByCodigoAndIdNot(String codigo, Integer id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_botao SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);

    @Query(value = "select distinct b.id as id_botao, b.codigo, b.estado as estado_botao, b.botao_icon,"
    + "b.descricao, t.estado as estado_transacao, t.tipo_utilizador_fk as perfil"
    + " from dbo.dn_t_botao b"
    + " left join dbo.dn_t_transacao t on t.botao_fk = b.id"
    + " where ( t.estado in :estadoAtivoInativo or t.estado is null ) and b.estado in :estadoAtivoInativo", nativeQuery = true)
    List<BotaoDto> listarBotaoEPerfilAssociado(@Param("estadoAtivoInativo") List<Integer> estadoAtivoInativo);
}

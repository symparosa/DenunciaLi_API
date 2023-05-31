package app.api.denuncia.Repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.AuditoriaModel;

@Repository
@Transactional
public interface AuditoriaRepository extends JpaRepository<AuditoriaModel, Integer> {

        @Modifying
        @Query(value = "SELECT accao"
                        + ",data_criacao"
                        + ",estado"
                        + ",id_objeto"
                        + ",id_utilizador"
                        + ",tipo_objeto"
                        + ",valor_atual"
                        + ",valor_novo"
                        + " FROM dbo.dn_t_auditoria"
                        + " where accao =:accao"
                        + " AND tipo_objeto =:tipo_objeto "
                        + " AND data_criacao >=:data_inicio "
                        + " AND data_criacao < DATEADD(DAY, 1,:data_fim)", nativeQuery = true)
        List<Object[]> filtroAuditoria(@Param("accao") String accao, @Param("tipo_objeto") String tipo_objeto,
                        @Param("data_inicio") LocalDate data_inicio, @Param("data_fim") LocalDate data_fim);
}

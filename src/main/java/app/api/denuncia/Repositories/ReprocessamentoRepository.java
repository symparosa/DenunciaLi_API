package app.api.denuncia.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.api.denuncia.Models.ReprocessamentoModel;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ReprocessamentoRepository extends JpaRepository<ReprocessamentoModel, Integer> {

        @Modifying
        @Query(value = "select "
                        + "id,"
                        + "data_criacao,"
                        + "email_body,"
                        + "email_subject,"
                        + "email_user,"
                        + "estado,"
                        + "CASE"
                        + "    WHEN last_user_change = 1 THEN 'Automático'"
                        + "    ELSE 'Manual'"
                        + "END AS reprocessamento"
                        + " from dn_t_reprocessamento"
                        + " where data_criacao >=:data_inicio "
                        + " and data_criacao < DATEADD(DAY, 1,:data_fim) "
                        + " and estado=:estado", nativeQuery = true)
        List<Object[]> filtroReprocessamentoEmail(
                        @Param("data_inicio") LocalDate data_inicio,
                        @Param("data_fim") LocalDate data_fim,
                        @Param("estado") Integer estado);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_reprocessamento SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
        Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);

}

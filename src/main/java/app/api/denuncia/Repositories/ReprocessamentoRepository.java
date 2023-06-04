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
                        + "    WHEN estado <> 1 AND last_user_change = 1 THEN 'Automático'"
                        + "    WHEN estado <> 1 AND last_user_change <> 1 THEN 'Manual'"
                        + "    ELSE '-----'"
                        + "END AS reprocessamento"
                        + " from dn_t_reprocessamento"
                        + " where data_criacao >=:data_inicio "
                        + " and data_criacao < DATEADD(DAY, 1,:data_fim) "
                        + " and estado=:estado"
                        + " and tipo_reprocessamento_fk=:tipo_repro", nativeQuery = true)
        List<Object[]> filtroReprocessamentoEmail(
                        @Param("data_inicio") LocalDate data_inicio,
                        @Param("data_fim") LocalDate data_fim,
                        @Param("estado") Integer estado,
                        @Param("tipo_repro") Integer tipo_repro);

        @Modifying
        @Query(value = "select "
                        + "r.id AS idRepro,"
                        + "r.data_criacao AS dataCriacaoRepro,"
                        + "r.estado AS estadoRepro,"
                        + "CASE"
                        + "    WHEN r.estado <> 1 AND r.last_user_change = 1 THEN 'Automático'"
                        + "    WHEN r.estado <> 1 AND r.last_user_change <> 1 THEN 'Manual'"
                        + "    ELSE '-----'"
                        + "END AS reprocessamento,"
                        + "d.estado AS estadoDenuncia,"
                        + " d.data_criacao AS dataDenuncia,"
                        + "do_tp_c.valor AS tipoCrime,"
                        + "do_tp_q.valor AS tipoQueixa"
                        + " from dn_t_reprocessamento r "
                        + " left join dn_t_denuncia d on d.id = r.denuncia_fk"
                        + " left join dn_t_queixa q on q.id = d.queixa_fk"
                        + " left join dn_t_dominio do_tp_c on do_tp_c.id =q.tipo_crime_fk "
                        + " left join dn_t_dominio do_tp_q on do_tp_q.id =q.tipo_queixa_fk "
                        + " where r.data_criacao >=:data_inicio "
                        + " and r.data_criacao < DATEADD(DAY, 1,:data_fim) "
                        + " and r.estado=:estado"
                        + " and r.tipo_reprocessamento_fk=:tipo_repro", nativeQuery = true)
        List<Object[]> filtroReprocessamentoDenuncia(
                        @Param("data_inicio") LocalDate data_inicio,
                        @Param("data_fim") LocalDate data_fim,
                        @Param("estado") Integer estado,
                        @Param("tipo_repro") Integer tipo_repro);

        @Modifying
        @Query(value = "UPDATE dbo.dn_t_reprocessamento SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
        Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);

        @Modifying
        @Query(value = "select id from dn_t_reprocessamento where estado in (1,3) and tipo_reprocessamento_fk=:tipo", nativeQuery = true)
        List<Integer> getIdNaoRepro(@Param("tipo") int tipo);
}

package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.Tipo_QueixaModel;

@Repository
@Transactional
public interface Tipo_QueixaRepository extends JpaRepository<Tipo_QueixaModel, Integer> {

    Tipo_QueixaModel findById(@Param("id") int id);

    Tipo_QueixaModel findByNome(@Param("nome") String nome);

    List<Tipo_QueixaModel> findAllByEstado(@Param("estado") int estado);

    Tipo_QueixaModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

    @Query(value = "select * from tipo_queixa where id!=:id and nome=:nome", nativeQuery = true)
    Tipo_QueixaModel validarTipoQueixa(@Param("id") int id, @Param("nome") String nome);

    @Modifying
    @Query(value = "INSERT INTO tipo_queixa(data_criacao, nome, estado) VALUES (now(),?,1);", nativeQuery = true)
    int save(@Param("nome") String nome);

    @Modifying
    @Query(value = "update tipo_queixa set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
    int ativar_desativarTipoQueixa(@Param("estado") int estado, @Param("id") int id);

    @Modifying
    @Query(value = "UPDATE tipo_queixa SET data_atualizacao = now(), nome=:nome where id=:id and estado = 1", nativeQuery = true)
    int atualizarTipoQueixa(@Param("nome") String nome, @Param("id") int id);
}

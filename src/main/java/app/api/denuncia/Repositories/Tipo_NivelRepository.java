package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.Tipo_NivelModel;

@Repository
@Transactional
public interface Tipo_NivelRepository extends JpaRepository<Tipo_NivelModel, Integer> {

    Tipo_NivelModel findById(@Param("id") int id);

    Tipo_NivelModel findByNome(@Param("nome") String nome);

    List<Tipo_NivelModel> findAllByEstado(@Param("estado") int estado);

    Tipo_NivelModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

    @Query(value = "select * from tipo_nivel where id!=:id and nome=:nome", nativeQuery = true)
    Tipo_NivelModel validarTipoNivel(@Param("id") int id, @Param("nome") String nome);

    @Modifying
    @Query(value = "INSERT INTO tipo_nivel(data_criacao, estado, nome) VALUES (now(),1,?);", nativeQuery = true)
    int save(@Param("nome") String nome);

    @Modifying
    @Query(value = "update tipo_nivel set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
    int ativar_desativarTipoNivel(@Param("estado") int estado, @Param("id") int id);

    @Modifying
    @Query(value = "UPDATE tipo_nivel SET data_atualizacao = now(), nome=:nome where id=:id and estado = 1", nativeQuery = true)
    int atualizarTipoNivel(@Param("nome") String nome, @Param("id") int id);
}

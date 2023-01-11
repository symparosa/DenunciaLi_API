package app.api.denuncia.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.api.denuncia.Models.Tipo_UtilizadorModel;

@Repository
@Transactional
public interface Tipo_UtilizadorRepository extends JpaRepository<Tipo_UtilizadorModel, Integer> {

    Tipo_UtilizadorModel findById(@Param("id") int id);

    Tipo_UtilizadorModel findByNome(@Param("nome") String nome);

    List<Tipo_UtilizadorModel> findAllByEstado(@Param("estado") int estado);

    Tipo_UtilizadorModel findByIdAndEstado(@Param("id") int id, @Param("estado") int estado);

    @Query(value = "select * from tipo_utilizador where id!=:id and nome=:nome", nativeQuery = true)
    Tipo_UtilizadorModel validarTipoUtilizador(@Param("id") int id, @Param("nome") String nome);

    @Modifying
    @Query(value = "INSERT INTO tipo_utilizador(data_criacao, estado, nome) VALUES (now(),1,?);", nativeQuery = true)
    int save(@Param("nome") String nome);

    @Modifying
    @Query(value = "update tipo_utilizador set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
    int ativar_desativarTipoUtilizador(@Param("estado") int estado, @Param("id") int id);

    @Modifying
    @Query(value = "UPDATE tipo_utilizador SET data_atualizacao=now(), nome=:nome where id=:id and estado = 1", nativeQuery = true)
    int atualizarTipoUtilizador(@Param("nome") String nome, @Param("id") int id);
}

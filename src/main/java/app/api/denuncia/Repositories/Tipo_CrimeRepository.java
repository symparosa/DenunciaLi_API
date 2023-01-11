package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.Tipo_CrimeModel;

@Repository
@Transactional
public interface Tipo_CrimeRepository extends JpaRepository<Tipo_CrimeModel, Integer> {

    Tipo_CrimeModel findById(@Param("id") int id);

    Tipo_CrimeModel findByNome(@Param("nome") String nome);

    List<Tipo_CrimeModel> findAllByEstado(@Param("estado") int estado);

    Tipo_CrimeModel findByIdAndEstado(@Param("id") int id,@Param("estado") int estado);

    @Query(value = "select * from tipo_crime where id!=:id and nome=:nome", nativeQuery = true)
    Tipo_CrimeModel validarTipoCrime(@Param("id") int id,@Param("nome") String nome);

    @Modifying
    @Query(value = "update tipo_crime set estado=:estado, data_atualizacao=now() where id=:id", nativeQuery = true)
    int ativar_desativarTipoCrime(@Param("estado") int fav, @Param("id") int id);

    @Modifying
    @Query(value = "INSERT INTO tipo_crime(nome, data_criacao, estado, logotipo) VALUES (?,now(),1,?);", nativeQuery = true)
    int save(@Param("nome") String nome, @Param("logotipo") String logotipo);
    
    @Modifying
    @Query(value = "UPDATE tipo_crime SET nome=:nome,data_atualizacao=now(),logotipo=:logotipo where id=:id and estado = 1", nativeQuery = true)
    int atualizarTipoCrime(@Param("nome") String nome, @Param("logotipo") String logotipo, @Param("id") int id);
}

package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DominioModel;

@Repository
@Transactional
public interface DominioRepository extends JpaRepository<DominioModel, Integer> {

    Boolean existsByDominio(String dominio);

    DominioModel findByDominio(String dominio);
    
    Boolean existsByIdAndDominio(int id, String dominio);

    DominioModel findByIdAndDominio(int id, String dominio);

    List<DominioModel> findByEstadoIn(List<Integer> estados);

    Boolean existsByDominioAndValor(String dominio, String valor);

    DominioModel findByDominioAndValor(String dominio, String valor);

    Boolean existsByDominioAndValorAndIdNot(String dominio, String valor, int id);

    List<DominioModel> findByDominioAndEstado(String dominio, int estados);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_dominio SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);
}
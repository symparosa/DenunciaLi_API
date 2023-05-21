package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;

@Repository
@Transactional
public interface EntidadeTipoCrimeRepository extends JpaRepository<EntidadeTipoCrimeModel, Integer> {

    Boolean existsByEntidade(EntidadeModel ent);

    Boolean existsByEntidadeAndTipoCrime(EntidadeModel ent, DominioModel tipo_crime);

    List<EntidadeTipoCrimeModel> findByEntidadeAndEstadoIn(EntidadeModel ent, List<Integer> estados);

    Boolean existsByEntidadeAndTipoCrimeAndIdNot(EntidadeModel ent, DominioModel tipo_crime, int id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_entidade_tipo_crime SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id=:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);
}

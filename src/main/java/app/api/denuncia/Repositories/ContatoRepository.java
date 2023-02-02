package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.ContatoModel;

@Repository
@Transactional
public interface ContatoRepository extends JpaRepository<ContatoModel, Integer> {

    Boolean existsByIdObjeto(int id);

    Boolean existsByValor(String valor);

    Boolean existsByValorAndIdNot(String valor, int id);

    List<ContatoModel> findByIdObjetoAndEstadoIn(int id, List<Integer> estados);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_contato SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id=:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado,@Param("user") int user, @Param("id") int id);
}

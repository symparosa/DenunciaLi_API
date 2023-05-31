package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.api.denuncia.Models.NoticiaModel;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface NoticiaRepository extends JpaRepository<NoticiaModel, Integer> {

    Boolean existsByTitulo(String titulo);

    Boolean existsByTituloAndIdNot(String titulo, int id);

    List<NoticiaModel> findByEstadoIn(List<Integer> estados);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_noticia SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);
}

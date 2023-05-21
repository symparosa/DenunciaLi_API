package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.BannerModel;

@Repository
@Transactional
public interface BannerRepository extends JpaRepository<BannerModel, Integer> {

    Boolean existsByTituloAndUrl(String titulo, String url);

    List<BannerModel> findByEstadoIn(List<Integer> estados);

    Boolean existsByTituloAndUrlAndIdNot(String titulo, String url, Integer id);

    @Modifying
    @Query(value = "UPDATE dbo.dn_t_banner SET data_atualizacao = GETDATE() ,estado =:estado, last_user_change=:user WHERE id =:id", nativeQuery = true)
    Integer alterarEstado(@Param("estado") int estado, @Param("user") int user, @Param("id") int id);
}

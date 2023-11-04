package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Models.LocalizacaoModel;
import app.api.denuncia.Statistic.Denuncia.LocalNome;

@Repository
@Transactional
public interface LocalizacaoRepository extends JpaRepository<LocalizacaoModel, String> {

        boolean existsById(String id);

        @Query(value = "SELECT id AS id_ilha, nome AS nome_ilha, nome_norm AS nome_norm_ilha"
                        + " FROM  dbo.dn_t_localizacao"
                        + " WHERE nivel_detalhe = 2", nativeQuery = true)
        List<Object[]> getIlhas();

        @Query(value = "SELECT id AS id_concelho, nome AS nome_concelho, nome_norm AS nome_norm_concelho"
                        + " FROM  dbo.dn_t_localizacao"
                        + " WHERE nivel_detalhe = 3", nativeQuery = true)
        List<Object[]> getConcelhos();

        @Query(value = "SELECT id AS id_localidade, nome AS nome_localidade, nome_norm AS nome_norm_localidade"
                        + " FROM  dbo.dn_t_localizacao"
                        + " WHERE nivel_detalhe = 6 and concelho =:concelho", nativeQuery = true)
        List<Object[]> getLocalizacao(@Param("concelho") String concelho);

        @Query(value = "SELECT nome FROM  dbo.dn_t_localizacao where id =:id", nativeQuery = true)
        LocalNome getNome(@Param("id") String id);
}

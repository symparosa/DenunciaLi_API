package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.LocalizacaoGetIlhasOutputDto;
import app.api.denuncia.Models.LocalizacaoModel;

@Repository
@Transactional
public interface LocalizacaoRepository extends JpaRepository<LocalizacaoModel, Integer> {

    Boolean existsById(String id);

    @Query(value = "SELECT idIlha, ilha"
            + " FROM view_localizacao_info"
            + " WHERE estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1 "
            + " group by idIlha"
            + " order by idIlha;", nativeQuery = true)
    List<LocalizacaoGetIlhasOutputDto> getIlhas();

    @Query(value = "SELECT id AS id_concelho, nome AS nome_concelho, nome_norm AS nome_norm_concelho"
            + " FROM  dbo.dn_t_localizacao"
            + " WHERE nivel_detalhe = 3", nativeQuery = true)
    List<Object[]> getConcelhos();

    @Query(value = "SELECT id AS id_localidade, nome AS nome_localidade, nome_norm AS nome_norm_localidade"
            + " FROM  dbo.dn_t_localizacao"
            + " WHERE nivel_detalhe = 6 and concelho =:concelho", nativeQuery = true)
    List<Object[]> getLocalizacao(@Param("concelho") String concelho);

}

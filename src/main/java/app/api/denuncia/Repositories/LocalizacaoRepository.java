package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.LocalizacaoGetConcelhosOutputDto;
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

    @Query(value = "SELECT idConcelho, concelho"
            + " FROM view_localizacao_info"
            + " WHERE estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1 "
            + " group by idConcelho"
            + " order by idConcelho;", nativeQuery = true)
    List<LocalizacaoGetConcelhosOutputDto> getConcelhos();
}

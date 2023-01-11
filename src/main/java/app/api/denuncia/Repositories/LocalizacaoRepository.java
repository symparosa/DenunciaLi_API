package app.api.denuncia.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.api.denuncia.Dto.LocalizacaoOutputDto;
import app.api.denuncia.Models.LocalizacaoModel;

@Repository
@Transactional
public interface LocalizacaoRepository extends JpaRepository<LocalizacaoModel, Integer> {

        @Query(value = "SELECT idLugar,longitudeLugar,latitudeLugar,lugar,"
                        + "idZona,longitudeZona,latitudeZona,zona,idConcelho,"
                        + "latitudeConcelho,longitudeConcelho,concelho,idIlha,"
                        + "latitudeIlha,longitudeIlha,ilha"
                        + " FROM view_localizacao_info"
                        + " WHERE estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1"
                        + " and (lugar LIKE %:localizacao% or zona LIKE %:localizacao% or concelho LIKE %:localizacao% or ilha LIKE %:localizacao%)", nativeQuery = true)
        List<LocalizacaoOutputDto> listarLocalizacoes(@Param("localizacao") String localizacao);

        @Query(value = "SELECT idLugar,longitudeLugar,latitudeLugar,lugar,"
                        + "idZona,longitudeZona,latitudeZona,zona,idConcelho,"
                        + "latitudeConcelho,longitudeConcelho,concelho,idIlha,"
                        + "latitudeIlha,longitudeIlha,ilha"
                        + " FROM view_localizacao_info"
                        + " WHERE estadoLugar = 1 and estadoZona = 1 and estadoConcelho = 1 and estadoIlha = 1"
                        + " and (idLugar=:id or idZona=:id or idConcelho=:id or idIlha=:id)", nativeQuery = true)
        List<LocalizacaoOutputDto> findById(@Param("id") int id);
}

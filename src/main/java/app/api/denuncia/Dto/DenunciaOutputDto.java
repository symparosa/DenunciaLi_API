package app.api.denuncia.Dto;

import java.util.Date;

public interface DenunciaOutputDto {
    int getId();

    Date getData_criacao();

    Date getData_ocorrencia();

    String getDescricao();

    String getGrau_parentesco();

    int getTipo_queixa_fk();

    String getTipoQueixa();

    int getTipo_crime_fk();

    String getTipoCrime();

    String getDescricao_endereco();

    String getPorta();

    String getRua();

    Integer getLocalizacao_fk();

    String getIlha();

    String getConcelho();

    String getZona();

    String getLugar();

    int getUtilizador_fk();

    String getUsername();

    String getLocalizacao_gps_map();
}

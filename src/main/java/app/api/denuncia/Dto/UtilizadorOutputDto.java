package app.api.denuncia.Dto;

import java.util.Date;

public interface UtilizadorOutputDto {
    int getId();

    String getApelido();

    String getBi();

    String getCni();

    Date getData_atualizacao();

    Date getData_criacao();

    Date getData_nascimento();

    String getEmail();

    int getEstado();

    String getFoto();

    String getGenero();

    String getNome();

    String getTelemovel();

    int getLocalizacao_fk();

    String getLongitude();

    String getLatitude();

    String getIlha();

    String getConcelho();

    String getZona();

    String getLugar();

    String getUsername();
}

package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface Tipo_NivelService {

    ResponseDto adicionarTipoNivel(String nome);

    ResponseDto listarTipoNiveisAtivos();

    ResponseDto listarTipoNiveisInativos();

    ResponseDto getTipoNivelById(int id);

    ResponseDto atualizarTipoNivel(String nome, int id);

    ResponseDto desativarTipoNivel(int id);

    ResponseDto ativarTipoNivel(int id);
}

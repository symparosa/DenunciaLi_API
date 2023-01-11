package app.api.denuncia.Services;

import app.api.denuncia.Dto.ResponseDto;

public interface Tipo_QueixaService {

    ResponseDto adicionarTipoQueixa(String nome);

    ResponseDto listarTipoQueixasAtivas();

    ResponseDto listarTipoQueixasInativas();

    ResponseDto getTipoQueixaById(int id);

    ResponseDto atualizarTipoQueixa(String nome, int id);

    ResponseDto desativarTipoQueixa(int id);

    ResponseDto ativarTipoQueixa(int id);
}

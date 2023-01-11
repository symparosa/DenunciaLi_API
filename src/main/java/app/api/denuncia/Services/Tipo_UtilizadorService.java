package app.api.denuncia.Services;

import app.api.denuncia.Dto.ResponseDto;

public interface Tipo_UtilizadorService {

    ResponseDto adicionarTipoUtilizador(String nome);

    ResponseDto listarTipoUtilizadoresAtivos();

    ResponseDto listarTipoUtilizadoresInativos();

    ResponseDto getTipoUtilizadorById(int id);

    ResponseDto atualizarTipoUtilizador(String nome, int id);

    ResponseDto desativarTipoUtilizador(int id);

    ResponseDto ativarTipoUtilizador(int id);
}

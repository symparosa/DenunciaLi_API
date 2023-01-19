package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface Tipo_ArquivoService {

    ResponseDto adicionarTipoArquivo(String nome);

    ResponseDto listarTipoArquivosAtivos();

    ResponseDto listarTipoArquivosInativos();

    ResponseDto getTipoArquivoById(int id);

    ResponseDto atualizarTipoArquivo(String nome, int id);

    ResponseDto desativarTipoArquivo(int id);

    ResponseDto ativarTipoArquivo(int id);
}

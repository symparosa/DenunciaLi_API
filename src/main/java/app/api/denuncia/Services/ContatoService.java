package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface ContatoService {

    ResponseDto adicionarContato(String telefone, String logotipo, String nome);

    ResponseDto listarContatoAtivos();

    ResponseDto listarContatoInativos();

    ResponseDto getContatoById(int id);

    ResponseDto atualizarContatoInfo(String telefone, String nome, String logotipo, int id);

    ResponseDto desativarContato(int id);

    ResponseDto ativarContato(int id);
}

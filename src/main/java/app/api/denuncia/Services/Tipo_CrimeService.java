package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface Tipo_CrimeService {

    ResponseDto adicionarTipoCrime(String nome, String logotipo);

    ResponseDto listarTipoCrimesAtivos();

    ResponseDto listarTipoCrimesInativos();

    ResponseDto getTipoCrimeById(int id);

    ResponseDto atualizarTipoCrime(String nome, String logotipo, int id);

    ResponseDto desativarTipoCrime(int id);

    ResponseDto ativarTipoCrime(int id);
}

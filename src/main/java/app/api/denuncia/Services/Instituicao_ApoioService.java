package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface Instituicao_ApoioService {

    ResponseDto adicionarInstituicaoApoio(String email, int endereco, String logotipo, String nome, String telefone,
            int tipo_crime_fk, String porta, String rua);

    ResponseDto listarInstituicaoDeApoioAtivos();

    ResponseDto listarInstituicaoDeApoioInativos();

    ResponseDto getInstituicaoApoioById(int id);

    ResponseDto getInstituicaoApoioByCrime(int id);

    ResponseDto atualizarInstituicaoApoio(String email, int endereco, String logotipo, String nome, String telefone,
            String porta, String rua, int id);

    ResponseDto desativarInstituicaoApoio(int id);

    ResponseDto ativarInstituicaoApoio(int id);
}

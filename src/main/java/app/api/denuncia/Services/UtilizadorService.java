package app.api.denuncia.Services;

import java.util.Date;

import app.api.denuncia.Dto.ResponseDto;

public interface UtilizadorService {

        ResponseDto adicionarUtilizador(String apelido, String bi, String cni, Date data_nascimento, String email,
                        String foto, String genero, String nome, String telemovel, int localizacao_fk, String username,
                        int tipoUtilizador_fk);

        ResponseDto listarUtilizadoresAtivos();

        ResponseDto listarUtilizadoresInativos();

        ResponseDto getUtilizadorById(int id);

        ResponseDto atualizarUtilizadorInfo(String apelido, String email, String nome, String telemovel,
                        int localizacao_fk,String foto,int id);

        ResponseDto desativarUtilizador(int id);

        ResponseDto ativarUtilizador(int id);
}

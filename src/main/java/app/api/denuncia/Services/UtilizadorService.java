package app.api.denuncia.Services;

import java.util.Date;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface UtilizadorService {

        ResponseDto adicionarUtilizador(String apelido, String bi, String cni, Date data_nascimento, String email,
                        String foto, String genero, String nome, String telemovel, Integer localizacao_fk, String username,
                        int tipoUtilizador_fk, String moradaGps_Map);

        ResponseDto listarUtilizadoresAtivos();

        ResponseDto listarUtilizadoresInativos();

        ResponseDto getUtilizadorById(int id);

        ResponseDto atualizarUtilizadorInfo(String apelido, String email, String nome, String telemovel,
        Integer localizacao_fk,String foto,String moradaGps_Map,int id);

        ResponseDto desativarUtilizador(int id);

        ResponseDto ativarUtilizador(int id);
}

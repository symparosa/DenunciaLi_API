package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.LocalizacaoOutputDto;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Repositories.LocalizacaoRepository;
import app.api.denuncia.Services.LocalizacaoService;

@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    private LocalizacaoRepository localizacaoRepository;

    public LocalizacaoServiceImpl(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    @Override
    public ResponseDto listarLocalizacoes(String localizacao) {

        ResponseDto response = new ResponseDto();

        try {
            List<LocalizacaoOutputDto> listaLocalizacao = localizacaoRepository.listarLocalizacoes(localizacao);

            if (listaLocalizacao != null && !listaLocalizacao.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaLocalizacao);
                response.setMessage(" Listar localizações com sucesso.");
                return response;
            } else if (listaLocalizacao == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar localizações.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de localizações está vazia.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto getLocalizacaoById(int id) {
        ResponseDto response = new ResponseDto();

        try {
            List<LocalizacaoOutputDto> listaLocalizacao = localizacaoRepository.findById(id);

            if (listaLocalizacao != null && !listaLocalizacao.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaLocalizacao);
                response.setMessage(" Listar localização com sucesso.");
                return response;
            } else if (listaLocalizacao == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar localização.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A localização não existe.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    public LocalizacaoRepository getLocalizacaoRepository() {
        return localizacaoRepository;
    }

    public void setLocalizacaoRepository(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }
}

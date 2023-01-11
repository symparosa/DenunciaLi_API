package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Models.Tipo_QueixaModel;
import app.api.denuncia.Repositories.Tipo_QueixaRepository;
import app.api.denuncia.Services.Tipo_QueixaService;

@Service
public class Tipo_QueixaServiceImpl implements Tipo_QueixaService {

    private Tipo_QueixaRepository tipoQueixaRepository;

    public Tipo_QueixaServiceImpl(Tipo_QueixaRepository tipoQueixaRepository) {
        this.tipoQueixaRepository = tipoQueixaRepository;
    }

    @Override
    public ResponseDto adicionarTipoQueixa(String nome) {

        ResponseDto response = new ResponseDto();

        try {

            if (tipoQueixaRepository.findByNome(nome) == null) {

                int tipoQueixaSave = tipoQueixaRepository.save(nome);

                if (tipoQueixaSave != 0) {
                    response.setResponseCode(1);
                    response.setResponseType(ResponseType.Sucesso);
                    response.setObject(null);
                    response.setMessage(" Tipo de queixa salvo com sucesso.");
                    return response;
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Falha ao salvar o tipo de queixa.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de queixa já existe.");
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
    public ResponseDto listarTipoQueixasAtivas() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_QueixaModel> listaTipoQueixas = tipoQueixaRepository.findAllByEstado(1);

            if (listaTipoQueixas != null && !listaTipoQueixas.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoQueixas);
                response.setMessage(" Listar tipo de queixas ativas com sucesso.");
                return response;
            } else if (listaTipoQueixas == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de queixas ativas.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de queixas ativas está vazia.");
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
    public ResponseDto listarTipoQueixasInativas() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_QueixaModel> listaTipoQueixas = tipoQueixaRepository.findAllByEstado(0);

            if (listaTipoQueixas != null && !listaTipoQueixas.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoQueixas);
                response.setMessage(" Listar tipo de queixas inativas com sucesso.");
                return response;
            } else if (listaTipoQueixas == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de queixas inativas.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de queixas inativas está vazia.");
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
    public ResponseDto getTipoQueixaById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Tipo_QueixaModel Tipo_Queixa = tipoQueixaRepository.findByIdAndEstado(id, 1);

            if (Tipo_Queixa != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Tipo_Queixa);
                response.setMessage(" Tipo de queixa encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de queixa não existe.");
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
    public ResponseDto atualizarTipoQueixa(String nome, int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoQueixaRepository.findByIdAndEstado(id, 1) != null) {

                if (tipoQueixaRepository.validarTipoQueixa(id, nome) == null) {

                    int result = tipoQueixaRepository.atualizarTipoQueixa(nome, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de queixa atualizado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao atualizar o tipo de queixa");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de queixa já existe.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de queixa não existe.");
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
    public ResponseDto desativarTipoQueixa(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoQueixaRepository.findById(id) != null) {

                if (tipoQueixaRepository.findById(id).getEstado() == 1) {

                    int result = tipoQueixaRepository.ativar_desativarTipoQueixa(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de queixa desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o Tipo de queixa.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de queixa já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de queixa não existe.");
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
    public ResponseDto ativarTipoQueixa(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoQueixaRepository.findById(id) != null) {

                if (tipoQueixaRepository.findById(id).getEstado() == 0) {

                    int result = tipoQueixaRepository.ativar_desativarTipoQueixa(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de queixa ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o Tipo de queixa.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de queixa já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de queixa não existe.");
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

    public Tipo_QueixaRepository getTipoQueixaRepository() {
        return tipoQueixaRepository;
    }

    public void setTipoQueixaRepository(Tipo_QueixaRepository tipoQueixaRepository) {
        this.tipoQueixaRepository = tipoQueixaRepository;
    }
}

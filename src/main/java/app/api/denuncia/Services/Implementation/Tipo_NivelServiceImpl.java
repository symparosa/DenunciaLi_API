package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Models.Tipo_NivelModel;
import app.api.denuncia.Repositories.Tipo_NivelRepository;
import app.api.denuncia.Services.Tipo_NivelService;

@Service
public class Tipo_NivelServiceImpl implements Tipo_NivelService {

    private Tipo_NivelRepository tipoNivelRepository;

    public Tipo_NivelServiceImpl(Tipo_NivelRepository tipoNivelRepository) {
        this.tipoNivelRepository = tipoNivelRepository;
    }

    @Override
    public ResponseDto adicionarTipoNivel(String nome) {

        ResponseDto response = new ResponseDto();

        try {

            if (tipoNivelRepository.findByNome(nome) == null) {

                int tipoNivelSave = tipoNivelRepository.save(nome);

                if (tipoNivelSave != 0) {
                    response.setResponseCode(1);
                    response.setResponseType(ResponseType.Sucesso);
                    response.setObject(null);
                    response.setMessage(" Tipo de nível salvo com sucesso.");
                    return response;
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Falha ao salvar o tipo de nível.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de nível já existe.");
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
    public ResponseDto listarTipoNiveisAtivos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_NivelModel> listaTipoNiveis = tipoNivelRepository.findAllByEstado(1);

            if (listaTipoNiveis != null && !listaTipoNiveis.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoNiveis);
                response.setMessage(" Listar tipo de níveis ativos com sucesso.");
                return response;
            } else if (listaTipoNiveis == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de níveis ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de níveis ativos está vazia.");
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
    public ResponseDto listarTipoNiveisInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_NivelModel> listaTipoNiveis = tipoNivelRepository.findAllByEstado(0);

            if (listaTipoNiveis != null && !listaTipoNiveis.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoNiveis);
                response.setMessage(" Listar tipo de níveis inativos com sucesso.");
                return response;
            } else if (listaTipoNiveis == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de níveis inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de níveis inativos está vazia.");
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
    public ResponseDto getTipoNivelById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Tipo_NivelModel Tipo_Nivel = tipoNivelRepository.findByIdAndEstado(id, 1);

            if (Tipo_Nivel != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Tipo_Nivel);
                response.setMessage(" Tipo de nível encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de nível não existe.");
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
    public ResponseDto atualizarTipoNivel(String nome, int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoNivelRepository.findByIdAndEstado(id,1) != null) {

                if (tipoNivelRepository.validarTipoNivel(id, nome) == null) {

                    int result = tipoNivelRepository.atualizarTipoNivel(nome, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de nível atualizado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao atualizar o tipo de nível.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de nível já existe.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de nível não existe.");
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
    public ResponseDto desativarTipoNivel(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoNivelRepository.findById(id) != null) {

                if (tipoNivelRepository.findById(id).getEstado() == 1) {

                    int result = tipoNivelRepository.ativar_desativarTipoNivel(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de nível desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o tipo de nível.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de nível já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de utilizador não existe.");
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
    public ResponseDto ativarTipoNivel(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoNivelRepository.findById(id) != null) {

                if (tipoNivelRepository.findById(id).getEstado() == 0) {

                    int result = tipoNivelRepository.ativar_desativarTipoNivel(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de nível ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o tipo de nível.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de nível já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de utilizador não existe.");
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

    public Tipo_NivelRepository getTipoNivelRepository() {
        return tipoNivelRepository;
    }

    public void setTipoNivelRepository(Tipo_NivelRepository tipoNivelRepository) {
        this.tipoNivelRepository = tipoNivelRepository;
    }
}

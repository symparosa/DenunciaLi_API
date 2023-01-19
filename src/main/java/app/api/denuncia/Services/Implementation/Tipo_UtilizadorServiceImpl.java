package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.Tipo_UtilizadorModel;
import app.api.denuncia.Repositories.Tipo_UtilizadorRepository;
import app.api.denuncia.Services.Tipo_UtilizadorService;

@Service
public class Tipo_UtilizadorServiceImpl implements Tipo_UtilizadorService {

    private Tipo_UtilizadorRepository tipo_UtilizadorRepository;

    public Tipo_UtilizadorServiceImpl(Tipo_UtilizadorRepository tipo_UtilizadorRepository) {
        this.tipo_UtilizadorRepository = tipo_UtilizadorRepository;
    }

    @Override
    public ResponseDto adicionarTipoUtilizador(String nome) {

        ResponseDto response = new ResponseDto();

        try {

            if (tipo_UtilizadorRepository.findByNome(nome) == null) {

                int tipoUtilizadorSave = tipo_UtilizadorRepository.save(nome);

                if (tipoUtilizadorSave != 0) {
                    response.setResponseCode(1);
                    response.setResponseType(ResponseType.Sucesso);
                    response.setObject(null);
                    response.setMessage(" Tipo de utilizador salvo com sucesso.");
                    return response;
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Falha ao salvar o tipo de utilizador.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de utilizador já existe.");
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
    public ResponseDto listarTipoUtilizadoresAtivos() {
        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_UtilizadorModel> listaTipoutilizadores = tipo_UtilizadorRepository.findAllByEstado(1);

            if (listaTipoutilizadores != null && !listaTipoutilizadores.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoutilizadores);
                response.setMessage(" Listar tipos de utilizador ativos com sucesso.");
                return response;
            } else if (listaTipoutilizadores == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipos de utilizador ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de utilizadores ativos está vazia.");
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
    public ResponseDto listarTipoUtilizadoresInativos() {
        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_UtilizadorModel> listaTipoutilizadores = tipo_UtilizadorRepository.findAllByEstado(0);

            if (listaTipoutilizadores != null && !listaTipoutilizadores.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoutilizadores);
                response.setMessage(" Listar tipos de utilizador inativos com sucesso.");
                return response;
            } else if (listaTipoutilizadores == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipos de utilizador inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de utilizadores inativos está vazia.");
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
    public ResponseDto getTipoUtilizadorById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Tipo_UtilizadorModel Tipo_Utilizador = tipo_UtilizadorRepository.findByIdAndEstado(id, 1);

            if (Tipo_Utilizador != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Tipo_Utilizador);
                response.setMessage(" Tipo de utilizador encontrado com sucesso.");
                return response;
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
    public ResponseDto atualizarTipoUtilizador(String nome, int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipo_UtilizadorRepository.findByIdAndEstado(id,1) != null) {

                if (tipo_UtilizadorRepository.validarTipoUtilizador(id, nome) == null) {

                    int result = tipo_UtilizadorRepository.atualizarTipoUtilizador(nome, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de utilizador atualizado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao atualizar o tipo de utilizador.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de utilizador já existe.");
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
    public ResponseDto desativarTipoUtilizador(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipo_UtilizadorRepository.findById(id) != null) {

                if (tipo_UtilizadorRepository.findById(id).getEstado() == 1) {

                    int result = tipo_UtilizadorRepository.ativar_desativarTipoUtilizador(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de utilizador desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o tipo de utilizador.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de utilizador já foi desativado.");
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
    public ResponseDto ativarTipoUtilizador(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipo_UtilizadorRepository.findById(id) != null) {

                if (tipo_UtilizadorRepository.findById(id).getEstado() == 0) {

                    int result = tipo_UtilizadorRepository.ativar_desativarTipoUtilizador(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de utilizador ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o tipo de utilizador.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de utilizador já foi ativado.");
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

    public Tipo_UtilizadorRepository getTipo_UtilizadorRepository() {
        return tipo_UtilizadorRepository;
    }

    public void setTipo_UtilizadorRepository(Tipo_UtilizadorRepository tipo_UtilizadorRepository) {
        this.tipo_UtilizadorRepository = tipo_UtilizadorRepository;
    }
}

package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Models.Tipo_CrimeModel;
import app.api.denuncia.Repositories.Tipo_CrimeRepository;
import app.api.denuncia.Services.Tipo_CrimeService;

@Service
public class Tipo_CrimeServiceImpl implements Tipo_CrimeService {

    private Tipo_CrimeRepository tipoCrimeRepository;
    private ContatoServiceImpl contatoServiceImpl;

    public Tipo_CrimeServiceImpl(Tipo_CrimeRepository tipoCrimeRepository, ContatoServiceImpl contatoServiceImpl) {
        this.tipoCrimeRepository = tipoCrimeRepository;
        this.contatoServiceImpl = contatoServiceImpl;
    }

    @Override
    public ResponseDto adicionarTipoCrime(String nome, String logotipo) {

        ResponseDto response = new ResponseDto();

        try {

            String msg = contatoServiceImpl.validarInput(logotipo, nome, 1);

            if (msg == null) {

                if (tipoCrimeRepository.findByNome(nome) == null) {

                    int tipoCrimeSave = tipoCrimeRepository.save(nome, logotipo);

                    if (tipoCrimeSave != 0) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de crime salvo com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao salvar o tipo de crime.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de crime já existe.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(msg);
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
    public ResponseDto listarTipoCrimesAtivos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_CrimeModel> listaTipoCrimes = tipoCrimeRepository.findAllByEstado(1);

            if (listaTipoCrimes != null && !listaTipoCrimes.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoCrimes);
                response.setMessage(" Listar tipos de crimes ativos com sucesso.");
                return response;
            } else if (listaTipoCrimes == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipos de crimes ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de crimes ativos está vazia.");
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
    public ResponseDto listarTipoCrimesInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_CrimeModel> listaTipoCrimes = tipoCrimeRepository.findAllByEstado(0);

            if (listaTipoCrimes != null && !listaTipoCrimes.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoCrimes);
                response.setMessage(" Listar tipos de crimes inativos com sucesso.");
                return response;
            } else if (listaTipoCrimes == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipos de crimes inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipos de crimes inativos está vazia.");
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
    public ResponseDto getTipoCrimeById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Tipo_CrimeModel Tipo_Crime = tipoCrimeRepository.findByIdAndEstado(id, 1);

            if (Tipo_Crime != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Tipo_Crime);
                response.setMessage(" Tipo de crime encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de crime não existe.");
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
    public ResponseDto atualizarTipoCrime(String nome, String logotipo, int id) {
        ResponseDto response = new ResponseDto();

        try {

            String msg = contatoServiceImpl.validarInput(logotipo, nome, id);

            if (msg == null) {

                if (tipoCrimeRepository.findByIdAndEstado(id, 1) != null) {

                    if (tipoCrimeRepository.validarTipoCrime(id, nome) == null) {

                        int result = tipoCrimeRepository.atualizarTipoCrime(nome, logotipo, id);

                        if (result == 1) {
                            response.setResponseCode(1);
                            response.setResponseType(ResponseType.Sucesso);
                            response.setObject(result);
                            response.setMessage(" Tipo de crime atualizado com sucesso.");
                            return response;
                        } else {
                            response.setResponseCode(0);
                            response.setResponseType(ResponseType.Erro);
                            response.setObject(null);
                            response.setMessage(" Falha ao atualizar o tipo de crime.");
                            return response;
                        }
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Tipo de crime já existe.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de crime não existe.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(msg);
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(e.getMessage());
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto desativarTipoCrime(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoCrimeRepository.findById(id) != null) {

                if (tipoCrimeRepository.findById(id).getEstado() == 1) {

                    int result = tipoCrimeRepository.ativar_desativarTipoCrime(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(id);
                        response.setMessage(" Tipo de crime desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o Tipo de crime.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de crime já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de crime não existe.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(e.getMessage());
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto ativarTipoCrime(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoCrimeRepository.findById(id) != null) {

                if (tipoCrimeRepository.findById(id).getEstado() == 0) {

                    int result = tipoCrimeRepository.ativar_desativarTipoCrime(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(id);
                        response.setMessage(" Tipo de crime ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o Tipo de crime.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de crime já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de crime não existe.");
                return response;
            }
        } catch (Exception e) {
            response.setResponseCode(0);
            response.setResponseType(ResponseType.Erro);
            response.setObject(e.getMessage());
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    public Tipo_CrimeRepository getTipoCrimeRepository() {
        return tipoCrimeRepository;
    }

    public void setTipoCrimeRepository(Tipo_CrimeRepository tipoCrimeRepository) {
        this.tipoCrimeRepository = tipoCrimeRepository;
    }

    public ContatoServiceImpl getContatoServiceImpl() {
        return contatoServiceImpl;
    }

    public void setContatoServiceImpl(ContatoServiceImpl contatoServiceImpl) {
        this.contatoServiceImpl = contatoServiceImpl;
    }
}

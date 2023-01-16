package app.api.denuncia.Services.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.UtilizadorOutputDto;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Repositories.UtilizadorRepository;
import app.api.denuncia.Services.UtilizadorService;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private UtilizadorRepository UtilizadorRepository;
    private ContatoServiceImpl contatoServiceImpl;

    public UtilizadorServiceImpl(app.api.denuncia.Repositories.UtilizadorRepository utilizadorRepository,
            ContatoServiceImpl contatoServiceImpl) {
        UtilizadorRepository = utilizadorRepository;
        this.contatoServiceImpl = contatoServiceImpl;
    }

    @Override
    public ResponseDto adicionarUtilizador(String apelido, String bi, String cni, Date data_nascimento, String email,
            String foto, String genero, String nome, String telemovel, int localizacao_fk, String username,
            int tipoUtilizador_fk) {

        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInput(apelido, bi, cni, data_nascimento, email, foto, genero, nome, telemovel,
                    localizacao_fk, username, tipoUtilizador_fk);

            if (msg == null) {

                if (contatoServiceImpl.validarTelemovel(telemovel) == 1) {

                    if (UtilizadorRepository.findByUsername(username) == null) {

                        if (UtilizadorRepository.findByEmail(email) == null) {

                            if ((cni != null && UtilizadorRepository.findByCni(cni) == null)
                                    || (bi != null && UtilizadorRepository.findByBi(bi) == null)) {

                                if (UtilizadorRepository.findByTelemovel(telemovel) == null) {

                                    int UtilizadorSave = UtilizadorRepository.save(apelido, bi, cni, data_nascimento,
                                            email, foto, genero, nome, telemovel, localizacao_fk, username,
                                            tipoUtilizador_fk);

                                    if (UtilizadorSave != 0) {
                                        response.setResponseCode(1);
                                        response.setResponseType(ResponseType.Sucesso);
                                        response.setObject(null);
                                        response.setMessage(" Utilizador salvo com sucesso.");
                                        return response;
                                    } else {
                                        response.setResponseCode(0);
                                        response.setResponseType(ResponseType.Erro);
                                        response.setObject(null);
                                        response.setMessage(" Falha ao salvar o utilizador.");
                                        return response;
                                    }
                                } else {
                                    response.setResponseCode(0);
                                    response.setResponseType(ResponseType.Erro);
                                    response.setObject(null);
                                    response.setMessage(" Número de telemovel já existe.");
                                    return response;
                                }
                            } else {
                                response.setResponseCode(0);
                                response.setResponseType(ResponseType.Erro);
                                response.setObject(null);
                                response.setMessage(" Número de identificação já existe.");
                                return response;
                            }
                        } else {
                            response.setResponseCode(0);
                            response.setResponseType(ResponseType.Erro);
                            response.setObject(null);
                            response.setMessage(" Email já existe.");
                            return response;
                        }
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Utilizador já existe.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(
                            "O número de telefone deve ter no máximo 7 dígitos (somente números são permitidos).");
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
    public ResponseDto listarUtilizadoresAtivos() {

        ResponseDto response = new ResponseDto();

        try {
            List<UtilizadorOutputDto> listaUtilizadors = UtilizadorRepository.listarUtilizadorsAtivos_Inativo(1);

            if (listaUtilizadors != null && !listaUtilizadors.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaUtilizadors);
                response.setMessage(" Listar utilizadores ativos com sucesso.");
                return response;
            } else if (listaUtilizadors == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar utilizadores ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de utilizadores ativos está vazia.");
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
    public ResponseDto listarUtilizadoresInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<UtilizadorOutputDto> listaUtilizadors = UtilizadorRepository.listarUtilizadorsAtivos_Inativo(0);

            if (listaUtilizadors != null && !listaUtilizadors.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaUtilizadors);
                response.setMessage(" Listar utilizadores inativos com sucesso.");
                return response;
            } else if (listaUtilizadors == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar utilizadores inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de utilizadores inativos está vazia.");
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
    public ResponseDto getUtilizadorById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            UtilizadorOutputDto Utilizador = UtilizadorRepository.findByIdUser(id);

            if (Utilizador != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Utilizador);
                response.setMessage(" Utilizador encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Utilizador não existe.");
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
    public ResponseDto atualizarUtilizadorInfo(String apelido, String email, String nome, String telemovel,
            int localizacao_fk, String foto, int id) {

        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInputUpdate(apelido, email, nome, telemovel, localizacao_fk, foto, id);

            if (msg == null) {

                if (UtilizadorRepository.findByIdAndEstado(id, 1) != null) {

                    if (contatoServiceImpl.validarTelemovel(telemovel) == 1) {

                        if (UtilizadorRepository.validarUtilizadorEmail(id, email) == null) {

                            if (UtilizadorRepository.validarUtilizadorTelemovel(id, telemovel) == null) {

                                int result = UtilizadorRepository.atualizarUtilizadorInfo(apelido, email, nome,
                                        telemovel,
                                        localizacao_fk, foto, id);

                                if (result == 1) {
                                    response.setResponseCode(1);
                                    response.setResponseType(ResponseType.Sucesso);
                                    response.setObject(null);
                                    response.setMessage(" Utilizador atualizado com sucesso.");
                                    return response;
                                } else {
                                    response.setResponseCode(0);
                                    response.setResponseType(ResponseType.Erro);
                                    response.setObject(null);
                                    response.setMessage(" Falha na atualização dos dados do utilizador.");
                                    return response;
                                }
                            } else {
                                response.setResponseCode(0);
                                response.setResponseType(ResponseType.Erro);
                                response.setObject(null);
                                response.setMessage(" Telemóvel já existe.");
                                return response;
                            }
                        } else {
                            response.setResponseCode(0);
                            response.setResponseType(ResponseType.Erro);
                            response.setObject(null);
                            response.setMessage(" Email já existe.");
                            return response;
                        }
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(
                                "O número de telefone deve ter no máximo 7 dígitos (somente números são permitidos).");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Utilizador não existe.");
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
    public ResponseDto desativarUtilizador(int id) {

        ResponseDto response = new ResponseDto();

        try {

            if (UtilizadorRepository.findById(id) != null) {

                if (UtilizadorRepository.findById(id).getEstado() == 1) {

                    int result = UtilizadorRepository.ativar_desativarUtilizador(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Utilizador desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o utilizador.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Utilizador já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Utilizador não existe.");
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
    public ResponseDto ativarUtilizador(int id) {

        ResponseDto response = new ResponseDto();

        try {

            if (UtilizadorRepository.findById(id) != null) {

                if (UtilizadorRepository.findById(id).getEstado() == 0) {

                    int result = UtilizadorRepository.ativar_desativarUtilizador(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Utilizador ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o utilizador.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Utilizador já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Utilizador não existe.");
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

    public String validarInput(String apelido, String bi, String cni, Date data_nascimento, String email,
            String foto, String genero, String nome, String telemovel, int localizacao_fk, String username,
            int tipoUtilizador_fk) {

        String msg = null;

        if (apelido == null) {
            return msg = "O apelido não pode ser null";
        } else if (bi == null && cni == null) {
            return msg = "O número de identificação não pode ser null";
        } else if (data_nascimento == null) {
            return msg = "A data de nascimento não pode ser null";
        } else if (email == null) {
            return msg = "O email não pode ser null";
        } else if (foto == null) {
            return msg = "A foto não pode ser null";
        } else if (genero == null) {
            return msg = "O genero não pode ser null";
        } else if (nome == null) {
            return msg = "O nome não pode ser null";
        } else if (telemovel == null) {
            return msg = "O telemovel não pode ser null";
        } else if (localizacao_fk == 0) {
            return msg = "A localização não pode ser 0";
        } else if (username == null) {
            return msg = "O username não pode ser null";
        } else if (tipoUtilizador_fk == 0) {
            return msg = "O tipo de utilizador não pode ser 0";
        } else {
            return msg;
        }
    }

    public String validarInputUpdate(String apelido, String email, String nome, String telemovel,
            int localizacao_fk, String foto, int id) {

        String msg = null;

        if (apelido == null) {
            return msg = "O apelido não pode ser null";
        } else if (email == null) {
            return msg = "O email não pode ser null";
        } else if (foto == null) {
            return msg = "A foto não pode ser null";
        } else if (nome == null) {
            return msg = "O nome não pode ser null";
        } else if (telemovel == null) {
            return msg = "O telemovel não pode ser null";
        } else if (localizacao_fk == 0) {
            return msg = "A localização não pode ser 0";
        } else if (id == 0) {
            return msg = "A id não pode ser 0";
        } else {
            return msg;
        }
    }

    public UtilizadorRepository getUtilizadorRepository() {
        return UtilizadorRepository;
    }

    public void setUtilizadorRepository(UtilizadorRepository UtilizadorRepository) {
        this.UtilizadorRepository = UtilizadorRepository;
    }
}

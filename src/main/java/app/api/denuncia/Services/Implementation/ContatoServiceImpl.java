package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Services.ContatoService;
import app.api.denuncia.Repositories.ContatoRepository;

@Service
public class ContatoServiceImpl implements ContatoService {

    private ContatoRepository ContatoRepository;

    public ContatoServiceImpl(ContatoRepository contatoRepository) {
        ContatoRepository = contatoRepository;
    }

    @Override
    public ResponseDto adicionarContato(String telefone, String logotipo, String nome) {

        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInput(logotipo, nome, 1);

            if (msg == null) {

                if (ContatoRepository.findByNome(nome) == null) {

                    if (ContatoRepository.findByTelefone(telefone) == null) {

                        if (validarTelemovel(telefone) == 1) {

                            int ContatoSave = ContatoRepository.save(telefone, logotipo, nome);

                            if (ContatoSave != 0) {
                                response.setResponseCode(1);
                                response.setResponseType(ResponseType.Sucesso);
                                response.setObject(null);
                                response.setMessage(" Contato salvo com sucesso.");
                                return response;
                            } else {
                                response.setResponseCode(0);
                                response.setResponseType(ResponseType.Erro);
                                response.setObject(null);
                                response.setMessage(" Falha ao salvar contato.");
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
                        response.setMessage(" Número de telefone já existe.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Contato já existe.");
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
    public ResponseDto listarContatoAtivos() {
        ResponseDto response = new ResponseDto();

        try {
            List<ContatoModel> listaContato = ContatoRepository.findAllByEstado(1);

            if (listaContato != null && !listaContato.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaContato);
                response.setMessage(" Listar contatos ativos com sucesso.");
                return response;
            } else if (listaContato == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar contatos ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de contatos ativos está vazia.");
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
    public ResponseDto listarContatoInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<ContatoModel> listaContato = ContatoRepository.findAllByEstado(0);

            if (listaContato != null && !listaContato.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaContato);
                response.setMessage(" Listar contatos inativos com sucesso.");
                return response;
            } else if (listaContato == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar contatos inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de contatos inativos está vazia.");
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
    public ResponseDto getContatoById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            ContatoModel Contato = ContatoRepository.findByIdAndEstado(id, 1);

            if (Contato != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Contato);
                response.setMessage(" Contato encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Contato não existe.");
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
    public ResponseDto atualizarContatoInfo(String telefone, String nome, String logotipo, int id) {
        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInput(logotipo, nome, id);

            if (msg == null) {

                if (ContatoRepository.findByIdAndEstado(id, 1) != null) {

                    if (ContatoRepository.validarContato(id, nome) == null) {

                        if (ContatoRepository.findByTelefone(telefone) == null) {

                            if (validarTelemovel(telefone) == 1) {

                                int result = ContatoRepository.atualizarContatoInfo(telefone, nome, logotipo, id);

                                if (result == 1) {
                                    response.setResponseCode(1);
                                    response.setResponseType(ResponseType.Sucesso);
                                    response.setObject(null);
                                    response.setMessage(" Contato atualizado com sucesso.");
                                    return response;
                                } else {
                                    response.setResponseCode(0);
                                    response.setResponseType(ResponseType.Erro);
                                    response.setObject(null);
                                    response.setMessage(" Falha ao atualizar contato.");
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
                            response.setMessage(" Número de telefone já existe.");
                            return response;
                        }
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Contato já existe.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Contato não existe.");
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
    public ResponseDto desativarContato(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (ContatoRepository.findById(id) != null) {

                if (ContatoRepository.findById(id).getEstado() == 1) {

                    int result = ContatoRepository.ativar_desativarContato(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Contato desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar contato");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Contato já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Contato não existe.");
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
    public ResponseDto ativarContato(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (ContatoRepository.findById(id) != null) {

                if (ContatoRepository.findById(id).getEstado() == 0) {

                    int result = ContatoRepository.ativar_desativarContato(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Contato ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar contato.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Contato já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Contato não existe.");
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

    public int validarTelemovel(String telefone) {
        if (telefone == null || (telefone.length() <= 7 && telefone.matches("[0-9]+"))) {
            return 1;
        } else {
            return 0;
        }
    }

    public String validarInput(String logotipo, String nome, int id) {

        String msg = null;

        if (nome == null) {
            return msg = "O nome não pode ser null";
        } else if (logotipo == null) {
            return msg = "O logotipo não pode ser null";
        } else if (id == 0) {
            return msg = "O id não pode ser 0";
        } else {
            return msg;
        }
    }

    public ContatoRepository getContatoRepository() {
        return ContatoRepository;
    }

    public void setContatoRepository(ContatoRepository contatoRepository) {
        ContatoRepository = contatoRepository;
    }
}

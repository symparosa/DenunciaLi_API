package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.Instituicao_ApoioOutputDto;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Repositories.Instituicao_ApoioRepository;
import app.api.denuncia.Repositories.Tipo_CrimeRepository;
import app.api.denuncia.Services.Instituicao_ApoioService;

@Service
public class Instituicao_ApoioServiceImpl implements Instituicao_ApoioService {

    private Instituicao_ApoioRepository InstituicaoApoioRepository;
    private Tipo_CrimeRepository tipo_CrimeRepository;
    private ContatoServiceImpl contatoServiceImpl;

    public Instituicao_ApoioServiceImpl(Instituicao_ApoioRepository instituicaoApoioRepository,
            Tipo_CrimeRepository tipo_CrimeRepository, ContatoServiceImpl contatoServiceImpl) {
        InstituicaoApoioRepository = instituicaoApoioRepository;
        this.tipo_CrimeRepository = tipo_CrimeRepository;
        this.contatoServiceImpl = contatoServiceImpl;
    }

    @Override
    public ResponseDto adicionarInstituicaoApoio(String email, int endereco, String logotipo, String nome,
            String telefone, int tipoCrime, String porta, String rua) {

        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInput(email, endereco, logotipo, nome, telefone, tipoCrime, porta, rua);

            if (msg == null) {

                if (contatoServiceImpl.validarTelemovel(telefone) == 1) {

                    if (InstituicaoApoioRepository.findByNome(nome) == null) {

                        if (InstituicaoApoioRepository.findByEmail(email) == null) {

                            if (InstituicaoApoioRepository.findByTelefone(telefone) == null) {

                                int InstituicaoApoioSave = InstituicaoApoioRepository.save(email, endereco, logotipo,
                                        nome,
                                        telefone, tipoCrime, porta, rua);

                                if (InstituicaoApoioSave != 0) {
                                    response.setResponseCode(1);
                                    response.setResponseType(ResponseType.Sucesso);
                                    response.setObject(null);
                                    response.setMessage(" Instituicao de apoio salvo com sucesso.");
                                    return response;
                                } else {
                                    response.setResponseCode(0);
                                    response.setResponseType(ResponseType.Erro);
                                    response.setObject(null);
                                    response.setMessage(" Falha ao salvar a Instituicao de apoio.");
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
                            response.setMessage(" Email já existe.");
                            return response;
                        }
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Instituicao de apoio já existe.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(
                            "O número de telefone deve ter 3 dígitos (somente números são permitidos).");
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
    public ResponseDto listarInstituicaoDeApoioAtivos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Instituicao_ApoioOutputDto> listaInstituicaoApoiosAtivos = InstituicaoApoioRepository
                    .listarInstituicaoDeApoioAtivos_Inativo(1);

            if (listaInstituicaoApoiosAtivos != null && !listaInstituicaoApoiosAtivos.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaInstituicaoApoiosAtivos);
                response.setMessage(" Listar instituições de apoio ativos com sucesso.");
                return response;
            } else if (listaInstituicaoApoiosAtivos == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar instituições de apoio ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de instituições de apoio ativos está vazia.");
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
    public ResponseDto listarInstituicaoDeApoioInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Instituicao_ApoioOutputDto> listaInstituicaoApoiosInativo = InstituicaoApoioRepository
                    .listarInstituicaoDeApoioAtivos_Inativo(0);

            if (listaInstituicaoApoiosInativo != null && !listaInstituicaoApoiosInativo.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaInstituicaoApoiosInativo);
                response.setMessage(" Listar instituições de apoio inativo com sucesso.");
                return response;
            } else if (listaInstituicaoApoiosInativo == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar instituições de apoio inativo.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de instituições de apoio inativo está vazia.");
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
    public ResponseDto getInstituicaoApoioById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Instituicao_ApoioOutputDto Instituicao_Apoio = InstituicaoApoioRepository.findByIdInst(id);

            if (Instituicao_Apoio != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Instituicao_Apoio);
                response.setMessage(" Instituição de apoio encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Instituição o de apoio não existe.");
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
    public ResponseDto atualizarInstituicaoApoio(String email, int endereco, String logotipo, String nome,
            String telefone, String porta, String rua, int id) {
        ResponseDto response = new ResponseDto();

        try {

            String msg = validarInputUpdate(email, endereco, logotipo, nome, telefone, porta, rua, id);

            if (msg == null) {

                if (InstituicaoApoioRepository.findByIdAndEstado(id, 1) != null) {

                    if (contatoServiceImpl.validarTelemovel(telefone) == 1) {

                        if (InstituicaoApoioRepository.validarInstituicaoApoioEmail(id, email) == null) {

                            if (InstituicaoApoioRepository.validarInstituicaoApoioTelefone(id, telefone) == null) {

                                int result = InstituicaoApoioRepository.atualizarInstituicaoApoio(email, endereco,
                                        logotipo,
                                        nome,
                                        telefone, porta, rua, id);

                                if (result == 1) {
                                    response.setResponseCode(1);
                                    response.setResponseType(ResponseType.Sucesso);
                                    response.setObject(null);
                                    response.setMessage(" Instituição  de apoio atualizado com sucesso.");
                                    return response;
                                } else {
                                    response.setResponseCode(0);
                                    response.setResponseType(ResponseType.Erro);
                                    response.setObject(null);
                                    response.setMessage(" Falha na atualização dos dados da instituição de apoio.");
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
                                "O número de telefone deve ter 3 dígitos (somente números são permitidos).");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Instituição  de apoio não existe.");
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
            response.setMessage(" ExceptionMessage = " + e.getMessage());
            return response;
        }
    }

    @Override
    public ResponseDto desativarInstituicaoApoio(int id) {

        ResponseDto response = new ResponseDto();

        try {

            if (InstituicaoApoioRepository.findById(id) != null) {

                if (InstituicaoApoioRepository.findById(id).getEstado() == 1) {

                    int result = InstituicaoApoioRepository.ativar_desativarInstituicaoApoio(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Instituição de apoio desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativado a Instituição de apoio .");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Instituição de apoio já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Instituição de apoio não existe.");
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
    public ResponseDto getInstituicaoApoioByCrime(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipo_CrimeRepository.findById(id) != null) {

                List<Instituicao_ApoioOutputDto> listaInstituicaoApoiosInativo = InstituicaoApoioRepository
                        .getInstituicaoApoioByCrime(id);

                if (listaInstituicaoApoiosInativo != null && !listaInstituicaoApoiosInativo.isEmpty()) {
                    response.setResponseCode(1);
                    response.setResponseType(ResponseType.Sucesso);
                    response.setObject(listaInstituicaoApoiosInativo);
                    response.setMessage(" Listar Instituição de apoio ativos por crime com sucesso.");
                    return response;
                } else if (listaInstituicaoApoiosInativo == null) {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Falha ao listar Insituição  de apoio ativos por crime.");
                    return response;
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" A lista de Instituição de apoio ativos por crime está vazia.");
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
            response.setObject(null);
            response.setMessage(" Falha no sistema.");
            return response;
        }
    }

    @Override
    public ResponseDto ativarInstituicaoApoio(int id) {

        ResponseDto response = new ResponseDto();

        try {

            if (InstituicaoApoioRepository.findById(id) != null) {

                if (InstituicaoApoioRepository.findById(id).getEstado() == 0) {

                    int result = InstituicaoApoioRepository.ativar_desativarInstituicaoApoio(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Instituição de apoio ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativado a Instituição de apoio .");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Instituição de apoio já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Instituição de apoio não existe.");
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

    public String validarInput(String email, int endereco, String logotipo, String nome,
            String telefone, int tipoCrime, String porta, String rua) {

        String msg = null;

        if (email == null) {
            return msg = "O email não pode ser null";
        } else if (endereco == 0) {
            return msg = "O endereco não pode ser 0";
        } else if (logotipo == null) {
            return msg = "O logotipo não pode ser null";
        } else if (nome == null) {
            return msg = "O nome não pode ser null";
        } else if (telefone == null) {
            return msg = "O telefone não pode ser null";
        } else if (tipoCrime == 0) {
            return msg = "O tipoCrime não pode ser 0";
        } else if (porta == null) {
            return msg = "A porta não pode ser null";
        } else if (rua == null) {
            return msg = "A rua não pode ser null";
        } else {
            return msg;
        }
    }

    public String validarInputUpdate(String email, int endereco, String logotipo, String nome,
            String telefone, String porta, String rua, int id) {

        String msg = null;

        if (id == 0) {
            return msg = "O id não pode ser 0";
        } else if (email == null) {
            return msg = "O email não pode ser null";
        } else if (endereco == 0) {
            return msg = "O endereco não pode ser 0";
        } else if (logotipo == null) {
            return msg = "O logotipo não pode ser null";
        } else if (nome == null) {
            return msg = "O nome não pode ser null";
        } else if (telefone == null) {
            return msg = "O telefone não pode ser null";
        } else if (porta == null) {
            return msg = "A porta não pode ser null";
        } else if (rua == null) {
            return msg = "A rua não pode ser null";
        } else {
            return msg;
        }
    }

    public Instituicao_ApoioRepository getInstituicaoApoioRepository() {
        return InstituicaoApoioRepository;
    }

    public void setInstituicaoApoioRepository(Instituicao_ApoioRepository InstituicaoApoioRepository) {
        this.InstituicaoApoioRepository = InstituicaoApoioRepository;
    }
}

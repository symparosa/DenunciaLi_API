package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Models.Tipo_ArquivoModel;
import app.api.denuncia.Repositories.Tipo_ArquivoRepository;
import app.api.denuncia.Services.Tipo_ArquivoService;

@Service
public class Tipo_ArquivoServiceImpl implements Tipo_ArquivoService {

    private Tipo_ArquivoRepository tipoArquivoRepository;

    public Tipo_ArquivoServiceImpl(Tipo_ArquivoRepository tipoArquivoRepository) {
        this.tipoArquivoRepository = tipoArquivoRepository;
    }

    @Override
    public ResponseDto adicionarTipoArquivo(String nome) {

        ResponseDto response = new ResponseDto();

        try {

            if (tipoArquivoRepository.findByNome(nome) == null) {

                int tipoArquivoSave = tipoArquivoRepository.save(nome);

                if (tipoArquivoSave != 0) {
                    response.setResponseCode(1);
                    response.setResponseType(ResponseType.Sucesso);
                    response.setObject(null);
                    response.setMessage(" Tipo de arquivo salvo com sucesso.");
                    return response;
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Falha ao salvar o tipo de arquivo.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de arquivo já existe.");
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
    public ResponseDto listarTipoArquivosAtivos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_ArquivoModel> listaTipoArquivos = tipoArquivoRepository.findAllByEstado(1);

            if (listaTipoArquivos != null && !listaTipoArquivos.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoArquivos);
                response.setMessage(" Listar tipo de arquivos ativos com sucesso.");
                return response;
            } else if (listaTipoArquivos == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de arquivos ativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipo de arquivos ativos está vazia.");
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
    public ResponseDto listarTipoArquivosInativos() {

        ResponseDto response = new ResponseDto();

        try {
            List<Tipo_ArquivoModel> listaTipoArquivos = tipoArquivoRepository.findAllByEstado(0);

            if (listaTipoArquivos != null && !listaTipoArquivos.isEmpty()) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(listaTipoArquivos);
                response.setMessage(" Listar tipo de arquivos inativos com sucesso.");
                return response;
            } else if (listaTipoArquivos == null) {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Falha ao listar tipo de arquivos inativos.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" A lista de tipo de arquivos inativos está vazia.");
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
    public ResponseDto getTipoArquivoById(int id) {
        ResponseDto response = new ResponseDto();

        try {

            Tipo_ArquivoModel Tipo_Arquivo = tipoArquivoRepository.findByIdAndEstado(id, 1);

            if (Tipo_Arquivo != null) {
                response.setResponseCode(1);
                response.setResponseType(ResponseType.Sucesso);
                response.setObject(Tipo_Arquivo);
                response.setMessage(" Tipo de arquivo encontrado com sucesso.");
                return response;
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de arquivo não existe.");
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
    public ResponseDto atualizarTipoArquivo(String nome, int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoArquivoRepository.findByIdAndEstado(id, 1) != null) {

                if (tipoArquivoRepository.validarTipoArquivo(id, nome) == null) {

                    int result = tipoArquivoRepository.atualizarTipoArquivo(nome, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de arquivo atualizado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao atualizar o tipo de arquivo.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de arquivo já existe.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de arquivo não existe.");
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
    public ResponseDto desativarTipoArquivo(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoArquivoRepository.findById(id) != null) {

                if (tipoArquivoRepository.findById(id).getEstado() == 1) {

                    int result = tipoArquivoRepository.ativar_desativarTipoArquivo(0, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de arquivo desativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao desativar o Tipo de arquivo.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de arquivo já foi desativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de arquivo não existe.");
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
    public ResponseDto ativarTipoArquivo(int id) {
        ResponseDto response = new ResponseDto();

        try {

            if (tipoArquivoRepository.findById(id) != null) {

                if (tipoArquivoRepository.findById(id).getEstado() == 0) {

                    int result = tipoArquivoRepository.ativar_desativarTipoArquivo(1, id);

                    if (result == 1) {
                        response.setResponseCode(1);
                        response.setResponseType(ResponseType.Sucesso);
                        response.setObject(null);
                        response.setMessage(" Tipo de arquivo ativado com sucesso.");
                        return response;
                    } else {
                        response.setResponseCode(0);
                        response.setResponseType(ResponseType.Erro);
                        response.setObject(null);
                        response.setMessage(" Falha ao ativar o Tipo de arquivo.");
                        return response;
                    }
                } else {
                    response.setResponseCode(0);
                    response.setResponseType(ResponseType.Erro);
                    response.setObject(null);
                    response.setMessage(" Tipo de arquivo já foi ativado.");
                    return response;
                }
            } else {
                response.setResponseCode(0);
                response.setResponseType(ResponseType.Erro);
                response.setObject(null);
                response.setMessage(" Tipo de arquivo não existe.");
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

    public Tipo_ArquivoRepository getTipoArquivoRepository() {
        return tipoArquivoRepository;
    }

    public void setTipoArquivoRepository(Tipo_ArquivoRepository tipoArquivoRepository) {
        this.tipoArquivoRepository = tipoArquivoRepository;
    }
}

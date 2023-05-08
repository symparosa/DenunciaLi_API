package app.api.denuncia.Services.Implementation;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.api.denuncia.AES.AES256Service;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.DenunciaOutputDto;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.ArquivoModel;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Repositories.DenunciaRepository;
import app.api.denuncia.Services.DenunciaService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    private DenunciaRepository denunciaRepository;
    private LocalizacaoService localService;
    private AES256Service aes256Service;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public DenunciaServiceImpl(DenunciaRepository denunciaRepository, LocalizacaoService localService,
            AES256Service aes256Service) {
        this.denunciaRepository = denunciaRepository;
        this.localService = localService;
        this.aes256Service = aes256Service;
    }

    @Override
    public ResponseModel adicionarDenuncia(String denu) {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();

        gf.clearList(msg);

        try {

            String decrypt = aes256Service.decrypt(denu);
            DenunciaModel denuncia = gson.fromJson(decrypt, DenunciaModel.class);

            if (decrypt != null && denuncia != null) {

                String metodo = "salvar", obj = "Localização";

                ResponseModel validar = validarCampos(denuncia);

                if (validar.getResponseCode() == 1) {

                    if ((denuncia.getQueixa().getLocalizacao() != null
                            && localService.existsLocalizacao(denuncia.getQueixa().getLocalizacao()))
                            || denuncia.getQueixa().getLocalizacao_mapa() != null) {

                        Date now = new Date();

                        denuncia.setData_criacao(now);
                        denuncia.setEstado(status.getAtivo());
                        denuncia.setLast_user_change(denuncia.getDenunciante().getId());

                        denuncia.getQueixa().setData_criacao(now);
                        denuncia.getQueixa().setEstado(status.getAtivo());
                        denuncia.getQueixa().setLast_user_change(denuncia.getDenunciante().getId());

                        if (denuncia.getQueixa().getLocalizacao_mapa() != null) {
                            denuncia.getQueixa().setLocalizacao(null);
                        }

                        List<ArquivoModel> arquivos = denuncia.getQueixa().getArquivos();

                        if(arquivos != null && arquivos.size()!=0){
                            arquivos.forEach(arquivo -> {
                                arquivo.setLast_user_change(denuncia.getDenunciante().getId());
                                arquivo.setData_criacao(now);
                                arquivo.setEstado(status.getAtivo());
                             });
                        }
                        
                        DenunciaModel denunciaSave = denunciaRepository.save(denuncia);

                        if (denunciaSave != null) {

                            List<ArquivoModel> arquivo = denunciaSave.getQueixa().getArquivos();

                            if (arquivo != null && !arquivo.isEmpty()) {

                                for (int i = 0; i < arquivo.size(); i++) {
                                    denunciaRepository.atualizarArquivo(denuncia.getDenunciante().getId(),
                                            denunciaSave.getQueixa().getId(),arquivo.get(i).getId());
                                }
                            }
                            return gf.validateGetSaveMsgWithObj(metodo, denunciaSave);
                        } else {
                            msg.add(message.getMessage02(metodo));
                            return gf.getResponseError(msg);
                        }
                    } else {
                        msg.add(message.getMessage06(obj));
                        return gf.getResponseError(msg);
                    }
                } else {
                    return validar;
                }
            } else {
                msg.add(message.getMessage14("decrypt"));
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public ResponseModel validarCampos(DenunciaModel denuncia) {

        String obj = "";

        if (denuncia.getQueixa().getDescricao() == null || denuncia.getQueixa().getDescricao().isEmpty()) {
            obj = "Descrição";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getData_ocorrencia() == null) {
            obj = "Data de ocorrência";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getReferencia_morada() == null
                || denuncia.getQueixa().getReferencia_morada().isEmpty()) {
            obj = "Referência de morada";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getCodigo_postal() == null
                || denuncia.getQueixa().getCodigo_postal().isEmpty()) {
            obj = "Código postal";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if ((denuncia.getQueixa().getLocalizacao_mapa() == null
                || denuncia.getQueixa().getLocalizacao_mapa().isEmpty())
                && denuncia.getQueixa().getLocalizacao() == null) {
            obj = "Localização";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getGrau_parentesco() == null) {
            obj = "Grau de parentesco";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getTipo_queixa() == null) {
            obj = "Tipo de queixa";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getQueixa().getTipo_crime() == null) {
            obj = "Tipo de crime";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (denuncia.getDenunciante() == null) {
            obj = "Denunciante";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else {
            return gf.getResponse(1, ResponseType.Sucesso, null, null);
        }
    }

    @Override
    public List<DenunciaOutputDto> listarDenunciasByUserId(int id) {
        return denunciaRepository.listarDenunciasByUserId(id);
    }

    // @Override
    // public ResponseModel getDenunciaById(int id) {
    // ResponseDto response = new ResponseDto();

    // try {
    // DenunciaOutputDto denuncia = denunciaRepository.findById(id);

    // if (denuncia != null) {
    // response.setResponseCode(1);
    // response.setResponseType(ResponseType.Sucesso);
    // response.setObject(denuncia);
    // response.setMessage(" Denúncia encontrada com sucesso.");
    // return response;
    // } else {
    // response.setResponseCode(0);
    // response.setResponseType(ResponseType.Erro);
    // response.setObject(null);
    // response.setMessage(" Denúncia não existe.");
    // return response;
    // }
    // } catch (Exception e) {
    // response.setResponseCode(0);
    // response.setResponseType(ResponseType.Erro);
    // response.setObject(null);
    // response.setMessage(" Falha no sistema.");
    // return response;
    // }
    // }
}

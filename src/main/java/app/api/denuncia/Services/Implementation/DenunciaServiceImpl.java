package app.api.denuncia.Services.Implementation;

import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.api.denuncia.AES.AES256Service;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.Denuncia;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Integration.Integrate.IntegrateService;
import app.api.denuncia.Models.ArquivoModel;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Repositories.DenunciaRepository;
import app.api.denuncia.Services.DenunciaService;
import app.api.denuncia.Services.DenuncianteService;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    private DenunciaRepository denunciaRepository;
    private LocalizacaoService localService;
    private AES256Service aes256Service;
    private IntegrateService integrateService;
    private DenuncianteService denuncianteService;
    private DominioService dominioService;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public DenunciaServiceImpl(DenunciaRepository denunciaRepository, LocalizacaoService localService,
            AES256Service aes256Service, IntegrateService integrateService, DenuncianteService denuncianteService,
            DominioService dominioService) {
        this.denunciaRepository = denunciaRepository;
        this.localService = localService;
        this.aes256Service = aes256Service;
        this.integrateService = integrateService;
        this.denuncianteService = denuncianteService;
        this.dominioService = dominioService;
    }

    // public int IdUserLogado() {
    // return auth.getDenunLogado().getId();
    // }

    public int IdUserLogado() {
        return 1;
    }

    @Override
    public Response adicionarDenuncia(String denu) {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();

        gf.clearList(msg);

        try {
            String decrypt = aes256Service.decrypt(denu);
            DenunciaModel denuncia = gson.fromJson(decrypt, DenunciaModel.class);

            if (decrypt != null && denuncia != null) {

                String metodo = "salvar", obj = "Localização";

                Response validar = validarCampos(denuncia);

                if (validar.getResponseCode() == 1) {

                    if ((denuncia.getQueixa().getLocalizacao() != null
                            && localService.existsLocalizacao(denuncia.getQueixa().getLocalizacao()))
                            || denuncia.getQueixa().getLocalizacao_mapa() != null) {

                        LocalDateTime now = LocalDateTime.now();

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

                        if (arquivos != null && arquivos.size() != 0) {
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
                                            denunciaSave.getQueixa().getId(), arquivo.get(i).getId());
                                }
                            }
                            Response rps = gf.validateGetSaveMsgWithObj(metodo, denunciaSave);

                            if (rps.getResponseCode() == 1) {

                                integrar(denunciaSave);
                            }
                            return rps;
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

    public Response validarCampos(DenunciaModel denuncia) {

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

    public void integrar(DenunciaModel denuncia) {

        DenuncianteModel denu = null;
        DominioModel grau_parentesco = null, tipo_queixa = null, tipo_crime = null, tipo_arquivo = null;

        if (denuncia.getDenunciante() != null) {
            denu = denuncianteService.findbyid(denuncia.getDenunciante().getId());
        }

        if (denuncia.getQueixa().getGrau_parentesco() != null) {
            grau_parentesco = dominioService.findbyid(denuncia.getQueixa().getGrau_parentesco().getId());
        }

        if (denuncia.getQueixa().getTipo_queixa() != null) {
            tipo_queixa = dominioService.findbyid(denuncia.getQueixa().getTipo_queixa().getId());
        }

        if (denuncia.getQueixa().getTipo_crime() != null) {
            tipo_crime = dominioService.findbyid(denuncia.getQueixa().getTipo_crime().getId());
        }

        if (denuncia != null) {

            denuncia.setDenunciante(denu);
            denuncia.getQueixa().setGrau_parentesco(grau_parentesco);
            denuncia.getQueixa().setTipo_queixa(tipo_queixa);
            denuncia.getQueixa().setTipo_crime(tipo_crime);

            if (denuncia.getQueixa().getArquivos() != null && !denuncia.getQueixa().getArquivos().isEmpty()) {

                for (int i = 0; i < denuncia.getQueixa().getArquivos().size(); i++) {

                    ArquivoModel arquivo = denuncia.getQueixa().getArquivos().get(i);

                    if (arquivo.getTipo_arquivo() != null) {
                        tipo_arquivo = dominioService.findbyid(arquivo.getTipo_arquivo().getId());
                    }

                    denuncia.getQueixa().getArquivos().get(i).setTipo_arquivo(tipo_arquivo);
                }
            }
            integrateService.Integracao(denuncia);
        }
    }

    @Override
    public Response listar_ocorrencias() {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .serializeNulls().create();

        gf.clearList(msg);

        try {

            String metodo = "listar";

            List<Denuncia> listadenuncia = listarDenunciasByUserId(IdUserLogado());
            String json = gson.toJson(listadenuncia);
            String encript = aes256Service.encrypt(json);
            return gf.validateMsgEncript(metodo, encript);

        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public List<Denuncia> listarDenunciasByUserId(int denu) {
        List<Object[]> results = denunciaRepository.listarDenunciasByUserId(denu);
        List<Denuncia> denuncias = new ArrayList<>();

        for (Object[] result : results) {
            Denuncia denuncia = new Denuncia();
            denuncia.setData_criacao(((Timestamp) result[0]).toLocalDateTime());
            denuncia.setEstado((int) result[1]);
            denuncia.setCodigo_postal((String) result[2]);
            denuncia.setData_ocorrencia(((Timestamp) result[3]).toLocalDateTime());
            denuncia.setDescricao((String) result[4]);
            denuncia.setLocalizacao_mapa((String) result[5]);
            denuncia.setReferencia_morada((String) result[6]);
            denuncia.setLocalizacao_nome((String) result[7]);
            denuncia.setLocalizacao_nome_norm((String) result[8]);
            denuncia.setGrau_parentesco((String) result[9]);
            denuncia.setTipo_crime((String) result[10]);
            denuncia.setTipo_queixa((String) result[11]);

            denuncias.add(denuncia);
        }

        return denuncias;
    }
}

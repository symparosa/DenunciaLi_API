package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.api.denuncia.AES.AES256Service;
import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Services.DenuncianteService;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Utilities.GlobalFunctions;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;

@Service
public class DenuncianteServiceImpl implements DenuncianteService {

    private DenuncianteRepository denRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private LocalizacaoService localService;
    private AES256Service aes256Service;
    private AuthenticationService auth;
    private EmailService emailService;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    @Value("${template.recover}")
    private String pathRecover;

    @Value("${template.registration}")
    private String pathRegistration;

    public DenuncianteServiceImpl(DenuncianteRepository denRepository, PasswordEncoder passwordEncoder,
            LocalizacaoService localService, AES256Service aes256Service, AuthenticationService auth,
            EmailService emailService) {
        this.denRepository = denRepository;
        this.passwordEncoder = passwordEncoder;
        this.localService = localService;
        this.aes256Service = aes256Service;
        this.auth = auth;
        this.emailService = emailService;
    }

    // public int IdUserLogado() {
    // return auth.getDenunLogado().getId();
    // }

    public int IdUserLogado() {
        return 1;
    }

    @Override
    public Response recuperarConta(String email) {

        String obj = "Email";
        gf.clearList(msg);

        try {

            if (denRepository.existsByUsername(email)) {

                var denun = denRepository.findByUsername(email).orElse(null);

                obj = "Denunciante";
                if (denun != null) {

                    String Subject = "Recuperação da Conta";
                    String ptxt = "Ol&aacute;,  " + denun.getNome()
                            + ". Foi solicitada a recupera&ccedil;&atilde;o de conta na plataforma <strong>DenunciaLi</strong>.";
                    String hash = gf.generateHash();

                    if (!denRepository.existsByHash(hash)) {

                        String metodo = "salvar";

                        Integer result = denRepository.updateHash(hash, denun.getId());

                        Response val = gf.validateGetUpdateMsg(metodo, result);

                        if (val.getResponseCode() == 1) {

                            msg.add(val.getMessage().get(0));

                            String html = gf.getTemplate(pathRecover);

                            Document doc = Jsoup.parse(html);
                            Element link = doc.getElementById("a_link");
                            Element p = doc.getElementById("p_corpo");
                            link.attr("href", "https://exemplo.com/nova-url");
                            p.html(ptxt);

                            EmailDetails emailDetail = gf.createEmail(email, doc.html(), Subject);

                            return emailService.sendEmail(emailDetail, msg);
                        } else {
                            return val;
                        }
                    } else {
                        msg.add(message.getMessage03());
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage06(obj));
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage06(obj));
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response eliminarConta(int estado) {

        gf.clearList(msg);

        try {

            String obj = "Denunciante";

            if (denRepository.existsById(IdUserLogado())) {

                if (gf.validateStatus(estado)) {

                    String metodo = "eliminar";

                    Integer result = denRepository.alterarEstado(estado, IdUserLogado());

                    Response saida = gf.validateGetUpdateMsg(metodo, result);

                    if (saida.getResponseCode() == 1) {
                        msg.add(saida.getMessage().get(0));

                        Integer logout = denRepository.logout(IdUserLogado());

                        if (logout != null) {

                            msg.add(message.getMessage13("Logout"));
                            return gf.getResponse(1, ResponseType.Sucesso, msg, null);

                        } else {
                            msg.add(message.getMessage14("logout"));
                            return gf.getResponseError(msg);
                        }
                    } else {
                        return saida;
                    }
                } else {
                    msg.add(message.getMessage07());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage06(obj));
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response alterarPassword(String username, String hash, String password, Boolean logado) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";
            String pass = aes256Service.decrypt(password);

            if (pass != null) {

                if (logado) {
                    // auth.getDenunLogado().getUsername()
                    if (username.equals("symparosa@gmail.com")) {

                        if (denRepository.existsByUsername(username)) {

                            var denu = denRepository.findByUsername(username).orElse(null);

                            Integer result = denRepository.changePassword(passwordEncoder.encode(pass), denu.getId());

                            return gf.validateGetUpdateMsg(metodo, result);
                        } else {
                            msg.add(message.getMessage12());
                            return gf.getResponseError(msg);
                        }
                    } else {
                        msg.add(message.getMessage16());
                        return gf.getResponseError(msg);
                    }
                } else {

                    if (denRepository.existsByUsernameAndHash(username, hash)) {

                        var denu = denRepository.findByUsername(username).orElse(null);

                        Integer result = denRepository.changePassword(passwordEncoder.encode(pass), denu.getId());

                        return gf.validateGetUpdateMsg(metodo, result);
                    } else {
                        msg.add(message.getMessage12());
                        return gf.getResponseError(msg);
                    }
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

    @Override
    public Boolean existsByIdAndEstado(int id, int estado) {
        return denRepository.existsByIdAndEstado(id, estado);
    }

    @Override
    public Response adicionar(String username, String nome) {

        gf.clearList(msg);

        String hash = gf.generateHash();
        String Subject = "Bem-vindo a DenunciaLi";
        String ptxt = "Ol&aacute;, " + nome + ". Seja bem-vindo(a) a plataforma <strong>DenunciaLi</strong>.";
        String metodo = "salvar";
        String titulo = "BEM-VINDO A DENUNCIALI";

        if (!denRepository.existsByUsername(username) && !denRepository.existsByHash(hash)) {

            DenuncianteModel den = new DenuncianteModel();

            den.setUsername(username);
            den.setNome(nome);
            den.setEstado(status.getAtivo());
            den.setData_criacao(LocalDateTime.now());
            den.setHash(hash);
            den.setContaConfirmada(false);

            DenuncianteModel denu = denRepository.save(den);

            Response val = gf.validateGetSaveMsgWithObj(metodo, denu);

            if (val.getResponseCode() == 1) {

                String html = gf.getTemplate(pathRegistration);

                Document doc = Jsoup.parse(html);
                Element div = doc.getElementById("div_titulo");
                Element p = doc.getElementById("p_corpo");
                Element link = doc.getElementById("a_link");
                link.attr("href", "https://exemplo.com/nova-url");
                div.html(titulo);
                p.html(ptxt);

                EmailDetails emailDetail = gf.createEmail(denu.getUsername(), doc.html(), Subject);
                msg.add(val.getMessage().get(0));

                return emailService.sendEmail(emailDetail, msg);
            } else {
                return val;
            }
        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response atualizar(DenuncianteModel denu) {

        gf.clearList(msg);

        try {

            String obj = "Denunciante";

            Response validar = validarCampos(denu);

            if (validar.getResponseCode() == 1) {
                // auth.getDenunLogado().getUsername()
                if (denu.getUsername().equals("symparosa@gmail.com")) {

                    String metodo = "salvar";
                    obj = "Localização";

                    if ((denu.getLocalizacao() != null && localService.existsLocalizacao(denu.getLocalizacao()))
                            || denu.getLocalizacao_mapa() != null) {

                        obj = "Documento de Identificacao";
                        if (!denRepository.existsByDocIdentificacaoAndUsernameNot(denu.getDocIdentificacao(),
                                denu.getUsername())) {

                            denu.setEstado(status.getAtivo());
                            denu.setData_criacao(LocalDateTime.now());
                            denu.setContaConfirmada(false);
                            denu.setData_atualizacao(LocalDateTime.now());
                            denu.setLast_user_change(IdUserLogado());

                            String id_local = null;
                            if (denu.getLocalizacao() != null) {
                                id_local = denu.getLocalizacao().getId();
                            }

                            Integer denun = denRepository.updateInfo(denu.getApelido(), denu.getCodigo_postal(),
                                    denu.getData_nascimento(), denu.getDocIdentificacao(), denu.getFoto_perfil(),
                                    denu.getGenero(), denu.getLocalizacao_mapa(), denu.getNome(),
                                    denu.getReferencia_morada(), id_local, IdUserLogado(),
                                    denu.getUsername());

                            return gf.validateGetUpdateMsg(metodo, denun);

                        } else {
                            msg.add(message.getMessage15(obj));
                            return gf.getResponseError(msg);
                        }
                    } else {
                        msg.add(message.getMessage06(obj));
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage16());
                    return gf.getResponseError(msg);
                }
            } else {
                return validar;
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public Response validarCampos(DenuncianteModel pessoa) {

        String obj = "";

        if (pessoa.getUsername() == null || pessoa.getUsername().isEmpty()) {
            obj = "Username";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            obj = "Nome";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getApelido() == null || pessoa.getApelido().isEmpty()) {
            obj = "Apelido";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getGenero() == null || pessoa.getGenero().isEmpty()) {
            obj = "Genero";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getDocIdentificacao() == null || pessoa.getDocIdentificacao().isEmpty()) {
            obj = "Documento de identificação";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getFoto_perfil() == null || pessoa.getFoto_perfil().isEmpty()) {
            obj = "Foto de perfil";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getData_nascimento() == null) {
            obj = "Data de nascimento";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else if (pessoa.getLocalizacao() == null && pessoa.getLocalizacao_mapa() == null) {
            obj = "Localização";
            msg.add(message.getMessage09(obj));
            return gf.getResponseError(msg);
        } else {
            return gf.getResponse(1, ResponseType.Sucesso, null, null);
        }
    }

    @Override
    public Response get_by_username(String username) {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .serializeNulls().create();
        gf.clearList(msg);

        try {

            if (denRepository.count() > 0) {
                // auth.getDenunLogado().getUsername()
                if (username.equals("symparosa@gmail.com")) {

                    String metodo = "listar", obj = "Denunciante";

                    DenuncianteModel denu = denRepository.findByUsername(username).orElse(null);
                    String json = gson.toJson(denu);
                    String encript = aes256Service.encrypt(json);
                    return gf.validateGetMsgWithObj(metodo, encript, obj);

                } else {
                    msg.add(message.getMessage16());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response validarSenhaAtual(String senha) {

        gf.clearList(msg);
        try {

            String pass = aes256Service.decrypt(senha);

            if (pass != null) {
                // auth.getDenunLogado().getUsername()
                DenuncianteModel denu = denRepository.findByUsername("symparosa@gmail.com").orElse(null);

                if (denu != null) {

                    if (passwordEncoder.matches(pass, denu.getPassword())) {
                        msg.add(message.getMessage18());
                        return gf.getResponse(1, ResponseType.Sucesso, msg, null);
                    } else {
                        msg.add(message.getMessage12());
                        return gf.getResponseError(msg);
                    }
                } else {
                    msg.add(message.getMessage16());
                    return gf.getResponseError(msg);
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

    @Override
    public DenuncianteModel findbyid(Integer id) {
        return denRepository.findById(id).orElse(null);
    }
}

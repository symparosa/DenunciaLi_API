package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.UtilizadorRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.EntidadeService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Services.ReprocessamentoService;
import app.api.denuncia.Services.UtilizadorService;
import app.api.denuncia.Utilities.GlobalFunctions;
import app.api.denuncia.Utilities.LocalDateTimeTypeAdapter;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private UtilizadorRepository userRepository;
    private LocalizacaoService localService;
    private DominioService domService;
    private EntidadeService entService;
    private EmailService emailService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService auth;
    private AES256Service aes256Service;
    private ReprocessamentoService reprocessamentoService;

    @Value("${template.recover}")
    private String PathRecover;

    @Value("${template.registration}")
    private String pathRegistration;

    @Value("${url_backoffice}")
    private String url;

    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public int IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    public UtilizadorServiceImpl(UtilizadorRepository userRepository, LocalizacaoService localService,
            DominioService domService, EntidadeService entService, EmailService emailService,
            PasswordEncoder passwordEncoder, AuthenticationService auth, AES256Service aes256Service,
            ReprocessamentoService reprocessamentoService) {
        this.userRepository = userRepository;
        this.localService = localService;
        this.domService = domService;
        this.entService = entService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.auth = auth;
        this.aes256Service = aes256Service;
        this.reprocessamentoService = reprocessamentoService;
    }

    @Override
    public Response adicionar_atualizar(UtilizadorModel user) {

        gf.clearList(msg);

        try {

            String metodo = "salvar", obj = "Localização";

            if (localService.existsLocalizacao(user.getLocalizacao())) {

                obj = "Tipo de utilizador";

                if (domService.existsTipo(user.getTipoUtilizador(), Domain.TIPO_UTILIZADOR.name())) {

                    obj = "Entidade";

                    if (entService.existsEntidade(user.getEntidade())) {

                        user.setLast_user_change(IdUserLogado());
                        
                        if (user.getId() != null) {

                            return updateUser(user, metodo);

                        } else {
                            user.setEstado(status.getAtivo());
                            user.setData_criacao(LocalDateTime.now());
                           
                            user.setContaConfirmada(false);
                            return insertUser(user, metodo);
                        }
                    } else {
                        msg.add(message.getMessage06(obj));
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

    public Response insertUser(UtilizadorModel uti, String metodo) {

        String hash = gf.generateHash();
        String Subject = "Registo de Utilizador";
        String ptxt = "Ol&aacute;, " + uti.getNome()
                + ". Voc&ecirc; foi convidado(a) a colaborar no <i>backoffice</i> da plataforma <strong>DenunciaLi</strong>.";
        String titulo = "REGISTO DE UTILIZADOR";

        if (!userRepository.existsByUsername(uti.getUsername()) && !userRepository.existsByHash(hash)) {

            uti.setData_atualizacao(null);
            uti.setHash(hash);
            UtilizadorModel user = userRepository.save(uti);

            Response val = gf.validateGetSaveMsgWithObj(metodo, user);

            if (val.getResponseCode() == 1) {

                String html = gf.getTemplate(pathRegistration);

                Document doc = Jsoup.parse(html);
                Element div = doc.getElementById("div_titulo");
                Element p = doc.getElementById("p_corpo");
                Element link = doc.getElementById("a_link");
                link.attr("href", url+"obj="+hash+"&obj_2="+uti.getUsername()+"&obj_t=1");
                div.html(titulo);
                p.html(ptxt);

                EmailDetails emailDetail = gf.createEmail(user.getUsername(), doc.html(), Subject);
                msg.add(val.getMessage().get(0));

                Response em = emailService.sendEmail(emailDetail, msg, val.getObject());

                if (em.getResponseCode() == 1) {
                    return em;
                } else {
                    reprocessamentoService.saveReprocessamentoEmail(emailDetail.getRecipient(),
                            emailDetail.getMsgBody(),
                            emailDetail.getSubject(), IdUserLogado());
                    return em;
                }
            } else {
                return val;
            }
        } else {
            msg.add(message.getMessage03());
            return gf.getResponseError(msg);
        }
    }

    public Response updateUser(UtilizadorModel uti, String metodo) {

        String obj = "Utilizador";

        if (userRepository.existsById(uti.getId())) {

            if (!userRepository.existsByUsernameAndIdNot(uti.getUsername(), uti.getId())) {

                uti.setData_atualizacao(LocalDateTime.now());
                UtilizadorModel user = userRepository.save(uti);
                return gf.validateGetSaveMsgWithObj(metodo, user);

            } else {
                msg.add(message.getMessage03());
                return gf.getResponseError(msg);
            }
        } else {
            msg.add(message.getMessage06(obj));
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Utilizador";

            if (userRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = userRepository.alterarEstado(estado, IdUserLogado(), id);
                    return gf.validateGetUpdateMsg(metodo, result);

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
    public Response listar() {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .serializeNulls().create();
        gf.clearList(msg);

        try {

            if (userRepository.count() > 0) {

                String metodo = "listar";

                List<UtilizadorModel> lista = userRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                String json = gson.toJson(lista);
                String encript = aes256Service.encrypt(json);
                return gf.validateMsgEncript(metodo, encript);

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
    public Response alterarPassword(String username, String hash, String password, Boolean logado) {

        gf.clearList(msg);

        try {

            String metodo = "salvar";
            String pass = aes256Service.decrypt(password);

            if (pass != null) {

                if (logado) {

                    if (username.equals(auth.getUtiLogado().getUsername())) {

                        if (userRepository.existsByUsername(username)) {

                            var user = userRepository.findByUsername(username).orElse(null);

                            Integer result = userRepository.changePassword(passwordEncoder.encode(pass), user.getId());

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

                    if (userRepository.existsByUsernameAndHash(username, hash)) {

                        var user = userRepository.findByUsername(username).orElse(null);

                        Integer result = userRepository.changePassword(passwordEncoder.encode(pass), user.getId());

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
    public Response recuperarConta(String email) {

        String obj = "Email";
        gf.clearList(msg);

        try {

            if (userRepository.existsByUsername(email)) {

                var user = userRepository.findByUsername(email).orElse(null);

                String Subject = "Recuperação da Conta",
                        ptxt = "Ol&aacute;,  " + user.getNome()
                                + ". Foi solicitada a recupera&ccedil;&atilde;o de conta no <i>backoffice</i> da plataforma <strong>DenunciaLi</strong>.";

                String hash = gf.generateHash();

                if (!userRepository.existsByHash(hash)) {

                    String metodo = "salvar";

                    Integer result = userRepository.updateHash(hash, user.getId());

                    Response val = gf.validateGetUpdateMsg(metodo, result);

                    if (val.getResponseCode() == 1) {

                        msg.add(val.getMessage().get(0));

                        String html = gf.getTemplate(PathRecover);

                        Document doc = Jsoup.parse(html);
                        Element link = doc.getElementById("a_link");
                        Element p = doc.getElementById("p_corpo");
                        link.attr("href",  url+"obj="+hash+"&obj_2="+email+"&obj_t=2");
                        p.html(ptxt);

                        EmailDetails emailDetail = gf.createEmail(email, doc.html(), Subject);

                        Response em = emailService.sendEmail(emailDetail, msg, null);

                        if (em.getResponseCode() == 1) {
                            return em;
                        } else {
                            reprocessamentoService.saveReprocessamentoEmail(emailDetail.getRecipient(),
                                    emailDetail.getMsgBody(), emailDetail.getSubject(), user.getId());
                            return em;
                        }
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
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public Response get_by_email(String email) {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .serializeNulls().create();
        gf.clearList(msg);

        try {

            if (userRepository.count() > 0) {

                String metodo = "listar", obj = "Utilizador";

                UtilizadorModel user = userRepository.findByUsername(email).orElse(null);

                if(user != null){
                    String json = gson.toJson(user);
                    String encript = aes256Service.encrypt(json);
                    return gf.validateGetMsgWithObj(metodo, encript, obj);
                }else{
                    msg.add(message.getMessage06(email));
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
    public Boolean existsByIdAndEstado(int id, int estado) {
        return userRepository.existsByIdAndEstado(id, estado);
    }

    @Override
    public Response validarSenhaAtual(String senha) {

        gf.clearList(msg);

        try {

            String pass = aes256Service.decrypt(senha);

            if (pass != null) {

                UtilizadorModel user = userRepository.findByUsername(auth.getUtiLogado().getUsername()).orElse(null);

                if (user != null) {

                    if (passwordEncoder.matches(pass, user.getPassword())) {
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
    public List<Integer> getIdUserContaNaoConfirmada() {
        return userRepository.getIdUserContaNaoConfirmada();
    }

    @Override
    public Integer eliminarUser(int estado, int idLast, int id) {
        return userRepository.alterarEstado(estado, idLast, id);
    }
}

package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.ContatoRepository;
import app.api.denuncia.Repositories.DominioRepository;
import app.api.denuncia.Repositories.UtilizadorRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.EntidadeService;
import app.api.denuncia.Services.LocalizacaoService;
import app.api.denuncia.Services.UtilizadorService;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private UtilizadorRepository userRepository;
    private LocalizacaoService localService;
    private DominioService domService;
    private EntidadeService entService;
    private EmailService emailService;
    private ContatoRepository contRepository;
    private DominioRepository domRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationService auth;

    @Value("${spring.recover}")
    private String PathRecover;

    @Value("${spring.registration}")
    private String pathRegistration;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public UtilizadorServiceImpl(UtilizadorRepository userRepository, LocalizacaoService localService,
            DominioService domService, EntidadeService entService, EmailService emailService,
            ContatoRepository contRepository, DominioRepository domRepository, AuthenticationService auth,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.localService = localService;
        this.domService = domService;
        this.entService = entService;
        this.emailService = emailService;
        this.contRepository = contRepository;
        this.domRepository = domRepository;
        this.auth = auth;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseModel adicionar_atualizar(UtilizadorModel user) {

        gf.clearList(msg);

        try {

            String metodo = "salvar", obj = "Localização";

            if (localService.existsLocalizacao(user.getLocalizacao())) {

                obj = "Tipo de utilizador";

                if (domService.existsTipo(user.getTipoUtilizador(), dom.getTipoUser())) {

                    obj = "Entidade";

                    if (entService.existsEntidade(user.getEntidade())) {

                        user.setEstado(status.getAtivo());
                        user.setData_criacao(new Date());
                        user.setLast_user_change(auth.getUserLogado().getId());
                        user.setContaConfirmada(false);

                        if (user.getId() != null) {

                            return updateUser(user, metodo);

                        } else {

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

    @Override
    public ResponseModel alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            String obj = "Utilizador";

            if (userRepository.existsById(id)) {

                if (gf.validateStatus(estado)) {

                    String metodo = "salvar";

                    Integer result = userRepository.alterarEstado(estado, auth.getUserLogado().getId(), id);
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
    public ResponseModel listar() {

        gf.clearList(msg);

        try {

            if (userRepository.count() > 0) {

                String metodo = "listar";

                List<UtilizadorModel> lista = userRepository.findByEstadoIn(gf.getStatusAtivoInativo());
                return gf.validateGetListMsg(metodo, lista);

            } else {
                msg.add(message.getMessage05());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    public ResponseModel insertUser(UtilizadorModel uti, String metodo) {

        String hash = gf.generateHash(), Subject = "Registo de Utilizador", ptxt = "Ol&aacute; " + uti.getNome()
                + ". Voc&ecirc; foi convidado a colaborar no <i>backoffice</i> da plataforma <strong>DenunciaLi.</strong>.";

        if (!userRepository.existsByUsername(uti.getUsername()) && !userRepository.existsByHash(hash)) {

            uti.setData_atualizacao(null);
            uti.setHash(hash);
            UtilizadorModel user = userRepository.save(uti);

            ResponseModel val = gf.validateGetSaveMsgWithObj(metodo, user);

            if (val.getResponseCode() == 1) {

                String html = gf.getTemplate(pathRegistration);

                Document doc = Jsoup.parse(html);
                Element p = doc.select("p").first();
                p.html(ptxt);

                EmailDetailsModel emailDetail = gf.createEmail(user.getUsername(), doc.html(), Subject);
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

    public ResponseModel updateUser(UtilizadorModel uti, String metodo) {

        String obj = "Utilizador";

        if (userRepository.existsById(uti.getId())) {

            if (!userRepository.existsByUsernameAndIdNot(uti.getUsername(), uti.getId())) {

                uti.setData_atualizacao(new Date());
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
    public ResponseModel alterarPassword(String username, String hash, String password) {

        gf.clearList(msg);

        try {

            if (userRepository.existsByUsernameAndHash(username, hash)) {

                String metodo = "salvar";

                var user = userRepository.findByUsername(username).orElse(null);

                Integer result = userRepository.changePassword(passwordEncoder.encode(password), user.getId());

                return gf.validateGetUpdateMsg(metodo, result);
            } else {
                msg.add(message.getMessage12());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }

    @Override
    public ResponseModel recuperarConta(String email) {

        String obj = "Email";
        gf.clearList(msg);

        try {

            // DominioModel tipoCont =
            // domRepository.findByDominioAndValor(dom.getTipoContato(), "EMAIL");
            // || contRepository.existsByTipoContatoAndValor(tipoCont, email)

            System.out.println("entrou 0 "+email);
            if (userRepository.existsByUsername(email)) {

                System.out.println("entrou 1");
                var user = userRepository.findByUsername(email).orElse(null);

                String Subject = "Recuperação da Conta",
                        ptxt = "Ol&aacute;  " + user.getNome()
                                + ". Foi solicitada a recupera&ccedil;&atilde;o de conta no <i>backoffice</i> da plataforma <strong>DenunciaLi</strong>.";

                String hash = gf.generateHash();

                if (!userRepository.existsByHash(hash)) {

                    String metodo = "salvar";

                    Integer result = userRepository.updateHash(hash, user.getId());

                    ResponseModel val = gf.validateGetUpdateMsg(metodo, result);

                    if (val.getResponseCode() == 1) {

                        msg.add(val.getMessage().get(0));

                        System.out.println("entrou ate aqui");

                        String html = gf.getTemplate(PathRecover);

                        Document doc = Jsoup.parse(html);
                        Element p = doc.select("p").first();
                        p.html(ptxt);

                        EmailDetailsModel emailDetail = gf.createEmail(email, doc.html(), Subject);

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
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}

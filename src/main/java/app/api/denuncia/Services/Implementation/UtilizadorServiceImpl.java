package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

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
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.UtilizadorService;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private UtilizadorRepository userRepository;
    private LocalizacaoServiceImpl localServiceImpl;
    private DominioServiceImpl domServiceImpl;
    private EntidadeServiceImpl entServiceImpl;
    private EmailService emailService;
    private ContatoRepository contRepository;
    private DominioRepository domRepository;

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public UtilizadorServiceImpl(UtilizadorRepository userRepository, LocalizacaoServiceImpl localServiceImpl,
            DominioServiceImpl domServiceImpl, EntidadeServiceImpl entServiceImpl, EmailService emailService,
            ContatoRepository contRepository, DominioRepository domRepository) {
        this.userRepository = userRepository;
        this.localServiceImpl = localServiceImpl;
        this.domServiceImpl = domServiceImpl;
        this.entServiceImpl = entServiceImpl;
        this.emailService = emailService;
        this.contRepository = contRepository;
        this.domRepository = domRepository;
    }

    @Override
    public ResponseModel adicionar_atualizar(UtilizadorModel user) {

        gf.clearList(msg);

        try {

            String metodo = "salvar", obj = "Localização";

            if (localServiceImpl.existsLocalizacao(user.getLocalizacao())) {

                obj = "Tipo de utilizador";

                if (domServiceImpl.existsTipo(user.getTipoUtilizador(), dom.getTipoUser())) {

                    obj = "Entidade";

                    if (entServiceImpl.existsEntidade(user.getEntidade())) {

                        user.setEstado(status.getAtivo());
                        user.setData_criacao(new Date());
                        user.setLast_user_change(gf.getId_user_logado());
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

                    Integer result = userRepository.alterarEstado(estado, gf.getId_user_logado(), id);
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

        String nome = "Elisângela";
        String hash = gf.generateHash(), Subject = "Registo de Utilizador", ptxt = "Ol&aacute; " + nome
                + ". Voc&ecirc; foi convidado a colaborar no <i>backoffice</i> da plataforma <strong>DenunciaLi.</strong>.";
        ;

        if (!userRepository.existsByUsername(uti.getUsername()) && !userRepository.existsByHash(hash)) {

            uti.setData_atualizacao(null);
            uti.setHash(hash);
            UtilizadorModel user = userRepository.save(uti);

            ResponseModel val = gf.validateGetSaveMsgWithObj(metodo, user);

            if (val.getResponseCode() == 1) {

                String html = gf.getTemplate(gf.getPathRegistration());

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
    public ResponseModel alterarPassword(String email, String hash, String password) {

        gf.clearList(msg);

        try {

            if (userRepository.existsByUsernameAndHash(email, hash)) {

                String metodo = "salvar";

                int idUser = userRepository.findByUsername(gf.getUsername()).getId();

                Integer result = userRepository.changePassword(password, gf.getId_user_logado(), idUser);

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

        String nome = "Elisângela";
        String obj = "Email";
        String Subject = "Recuperação da Conta",
                ptxt = "Ol&aacute;  " + nome
                        + ". Foi solicitada a recupera&ccedil;&atilde;o de conta no <i>backoffice</i> da plataforma <strong>DenunciaLi</strong>.";

        gf.clearList(msg);

        try {

            DominioModel tipoCont = domRepository.findByDominioAndValor(dom.getTipoContato(), "EMAIL");

            if (userRepository.existsByUsername(email) || contRepository.existsByTipoContatoAndValor(tipoCont, email)) {

                String hash = gf.generateHash();

                if (!userRepository.existsByHash(hash)) {

                    String metodo = "salvar";

                    int idUser = userRepository.findByUsername(gf.getUsername()).getId();

                    Integer result = userRepository.updateHash(hash, gf.getId_user_logado(), idUser);

                    ResponseModel val = gf.validateGetUpdateMsg(metodo, result);

                    if (val.getResponseCode() == 1) {

                        msg.add(val.getMessage().get(0));

                        String html = gf.getTemplate(gf.getPathRecover());

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

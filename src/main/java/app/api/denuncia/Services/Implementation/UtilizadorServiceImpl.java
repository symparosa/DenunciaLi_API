package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.AES.AES256;
import app.api.denuncia.Constants.Domain;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;
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

    private Domain dom = new Domain();
    private Status status = new Status();
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public UtilizadorServiceImpl(UtilizadorRepository userRepository, LocalizacaoServiceImpl localServiceImpl,
            DominioServiceImpl domServiceImpl, EntidadeServiceImpl entServiceImpl, EmailService emailService) {
        this.userRepository = userRepository;
        this.localServiceImpl = localServiceImpl;
        this.domServiceImpl = domServiceImpl;
        this.entServiceImpl = entServiceImpl;
        this.emailService = emailService;
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

        if (!userRepository.existsByUsername(uti.getUsername())) {

            uti.setData_atualizacao(null);
            uti.setHash(gf.generateHash());
            UtilizadorModel user = userRepository.save(uti);
            return createEmailSave(metodo, user, user.getUsername(), user.getHash());

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

    public ResponseModel createEmailSave(String metodo, Object obj, String email, String hash) {

        ResponseModel val = gf.validateGetSaveMsgWithObj(metodo, obj);

        if (val.getResponseCode() == 1) {

            EmailDetailsModel emailDetail = new EmailDetailsModel();

            emailDetail.setRecipient(email);
            emailDetail.setMsgBody("ola teste send email seu hash é " + hash);
            emailDetail.setSubject("teste send email");

            return emailService.sendEmail(emailDetail, val.getMessage().get(0));
        } else {
            return val;
        }
    }

    public ResponseModel createEmailUpdate(String metodo, Integer result, String email, String hash) {

        ResponseModel val = gf.validateGetUpdateMsg(metodo, result);

        if (val.getResponseCode() == 1) {

            EmailDetailsModel emailDetail = new EmailDetailsModel();

            emailDetail.setRecipient(email);
            emailDetail.setMsgBody("ola teste send email seu hash é " + hash);
            emailDetail.setSubject("teste send email");

            return emailService.sendEmail(emailDetail, val.getMessage().get(0));
        } else {
            return val;
        }
    }

    @Override
    public ResponseModel alterarPassword(String hashAtual, String passwordNovo1, String passwordNovo2) {

        gf.clearList(msg);

        try {
            
            if (userRepository.existsByUsernameAndHash(gf.getUsername(), hashAtual)) {

                AES256 aes256 = new AES256();

                if (aes256.decrypt(passwordNovo1).equals(aes256.decrypt(passwordNovo2))) {

                    String metodo = "salvar";
                    String nvhash = gf.generateHash();
                    int idUser = userRepository.findByUsername(gf.getUsername()).getId();

                    System.out.println("user "+gf.getId_user_logado());
                    Integer result = userRepository.changePassword(passwordNovo1, nvhash, gf.getId_user_logado(),idUser);
                    return createEmailUpdate(metodo, result, gf.getUsername(), nvhash);

                } else {
                    msg.add(message.getMessage13());
                    return gf.getResponseError(msg);
                }
            } else {
                msg.add(message.getMessage12());
                return gf.getResponseError(msg);
            }
        } catch (Exception e) {
            msg.add(message.getMessage04());
            return gf.getResponseError(msg);
        }
    }
}

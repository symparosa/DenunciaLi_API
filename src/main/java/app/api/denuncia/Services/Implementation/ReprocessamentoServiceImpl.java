package app.api.denuncia.Services.Implementation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import app.api.denuncia.Authentication.AuthenticationService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.ReprocessamentoDenuncia;
import app.api.denuncia.Dto.ReprocessamentoEmail;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Enums.DomainValue;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ReprocessamentoModel;
import app.api.denuncia.Repositories.ReprocessamentoRepository;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.ReprocessamentoService;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
public class ReprocessamentoServiceImpl implements ReprocessamentoService {

    private EmailService emailService;
    private AuthenticationService auth;
    private DominioService dominioService;
    private ReprocessamentoRepository reprocessamentoRepository;

    private String obj = "Reprocessamento";
    private Message message = new Message();
    private List<String> msg = new ArrayList<>();
    private GlobalFunctions gf = new GlobalFunctions();

    public Integer IdUserLogado() {
        return auth.getUtiLogado().getId();
    }

    public ReprocessamentoServiceImpl(EmailService emailService, AuthenticationService auth,
            DominioService dominioService, ReprocessamentoRepository reprocessamentoRepository) {
        this.emailService = emailService;
        this.auth = auth;
        this.dominioService = dominioService;
        this.reprocessamentoRepository = reprocessamentoRepository;
    }

    @Override
    public void saveReprocessamentoEmail(String recipient, String msgBody, String subject, int user_id) {

        DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                DomainValue.EMAIL.name());

        ReprocessamentoModel repro = new ReprocessamentoModel();

        repro.setData_criacao(LocalDateTime.now());
        repro.setEmail_user(recipient);
        repro.setEmail_body(msgBody);
        repro.setEmail_subject(subject);
        repro.setLast_user_change(user_id);
        repro.setTipo_reprocessamento(dom);
        repro.setEstado(1);

        ReprocessamentoModel saveRepro = reprocessamentoRepository.save(repro);

        if (saveRepro == null) {
            System.out.println("ERRO AO SALVAR REPROCESSAMENTO");
        } else {
            System.out.println("REPROCESSAMENTO SALVO COM SUCESSO");
        }
    }

    @Override
    public void saveReprocessamentoDenuncia(DenunciaModel denuncia) {

        DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                DomainValue.DENUNCIA.name());

        ReprocessamentoModel repro = new ReprocessamentoModel();

        repro.setData_criacao(LocalDateTime.now());
        repro.setDenuncia(denuncia);
        repro.setEstado(1);
        repro.setLast_user_change(denuncia.getDenunciante().getId());
        repro.setTipo_reprocessamento(dom);

        ReprocessamentoModel saveRepro = reprocessamentoRepository.save(repro);

        if (saveRepro == null) {
            System.out.println("ERRO AO SALVAR REPROCESSAMENTO");
        } else {
            System.out.println("REPROCESSAMENTO SALVO COM SUCESSO");
        }
    }

    @Override
    public Response filtroReprocessamentoEmail(LocalDate data_inicio, LocalDate data_fim, Integer estado) {

        gf.clearList(msg);

        try {

            if (reprocessamentoRepository.count() > 0) {

                String metodo = "listar";

                DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                        DomainValue.EMAIL.name());

                List<Object[]> results = reprocessamentoRepository.filtroReprocessamentoEmail(data_inicio, data_fim,
                        estado, dom.getId());

                List<ReprocessamentoEmail> reprocessamentoEmails = new ArrayList<>();

                for (Object[] result : results) {
                    ReprocessamentoEmail reprocessamentoEmail = new ReprocessamentoEmail();
                    reprocessamentoEmail.setId((Integer) result[0]);
                    reprocessamentoEmail.setData_criacao(((Timestamp) result[1]).toLocalDateTime());
                    reprocessamentoEmail.setEmail_body((String) result[2]);
                    reprocessamentoEmail.setEmail_subject((String) result[3]);
                    reprocessamentoEmail.setEmail_user((String) result[4]);
                    reprocessamentoEmail.setEstado((Integer) result[5]);
                    reprocessamentoEmail.setReprocessamento((String) result[6]);

                    reprocessamentoEmails.add(reprocessamentoEmail);
                }

                return gf.validateGetListMsg(metodo, reprocessamentoEmails);

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
    public Response alterarEstado(int id, int estado) {

        gf.clearList(msg);

        try {

            if (reprocessamentoRepository.existsById(id)) {

                if (gf.validateStatusReprocessamento(estado)) {

                    String metodo = "salvar";
                    Integer userId = 1;

                    if (IdUserLogado() != null) {
                        userId = IdUserLogado();
                    }

                    Integer result = reprocessamentoRepository.alterarEstado(estado, userId, id);
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
    public Response reprocessamentoManualEmail(int id) {

        gf.clearList(msg);

        try {

            if (reprocessamentoRepository.existsById(id)) {

                ReprocessamentoModel repro = reprocessamentoRepository.findById(id).orElse(null);

                if (repro != null) {

                    DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                            DomainValue.EMAIL.name());

                    if ((repro.getEstado() == 1 || repro.getEstado() == 3)
                            && dom.getId() == repro.getTipo_reprocessamento().getId()) {

                        EmailDetails emailDetail = gf.createEmail(repro.getEmail_user(), repro.getEmail_body(),
                                repro.getEmail_subject());

                        Response val = emailService.sendEmail(emailDetail, msg);

                        String metodo = "salvar";
                        Integer userId = 1;

                        if (IdUserLogado() != null) {
                            userId = IdUserLogado();
                        }

                        Integer result = null;
                        if (val.getResponseCode() == 1) {
                            result = reprocessamentoRepository.alterarEstado(2, userId, repro.getId());
                        } else {
                            result = reprocessamentoRepository.alterarEstado(3, userId, repro.getId());
                        }

                        if (result != null) {

                            msg.add(message.getMessage01(metodo));
                            return gf.getResponse(1, ResponseType.Sucesso, msg, null);

                        } else {
                            msg.add(message.getMessage02(metodo));
                            return gf.getResponseError(msg);
                        }
                    } else {
                        msg.add(message.getMessage19());
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
    public Response filtroReprocessamentoDenuncia(LocalDate data_inicio, LocalDate data_fim, Integer estado) {

        gf.clearList(msg);

        try {

            if (reprocessamentoRepository.count() > 0) {

                String metodo = "listar";

                DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                        DomainValue.DENUNCIA.name());

                List<Object[]> results = reprocessamentoRepository.filtroReprocessamentoDenuncia(data_inicio, data_fim,
                        estado, dom.getId());

                List<ReprocessamentoDenuncia> reprocessamentoDenuncias = new ArrayList<>();

                for (Object[] result : results) {
                    ReprocessamentoDenuncia reprocessamentoDenuncia = new ReprocessamentoDenuncia();
                    reprocessamentoDenuncia.setIdRepro((Integer) result[0]);
                    reprocessamentoDenuncia.setDataCriacaoRepro(((Timestamp) result[1]).toLocalDateTime());
                    reprocessamentoDenuncia.setEstadoRepro((Integer) result[2]);
                    reprocessamentoDenuncia.setReprocessamento((String) result[3]);
                    reprocessamentoDenuncia.setEstadoDenuncia((Integer) result[4]);
                    reprocessamentoDenuncia.setDataDenuncia(((Timestamp) result[5]).toLocalDateTime());
                    reprocessamentoDenuncia.setTipoCrime((String) result[6]);
                    reprocessamentoDenuncia.setTipoQueixa((String) result[7]);

                    reprocessamentoDenuncias.add(reprocessamentoDenuncia);
                }

                return gf.validateGetListMsg(metodo, reprocessamentoDenuncias);

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
    public ReprocessamentoModel getReprocessamentoById(int id) {
        return reprocessamentoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Integer> getIdEmailNaoRepro() {

        DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                DomainValue.EMAIL.name());

        return reprocessamentoRepository.getIdNaoRepro(dom.getId());
    }

    @Override
    public List<Integer> getIdDenunciaNaoRepro() {

        DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                DomainValue.DENUNCIA.name());

        return reprocessamentoRepository.getIdNaoRepro(dom.getId());
    }
}

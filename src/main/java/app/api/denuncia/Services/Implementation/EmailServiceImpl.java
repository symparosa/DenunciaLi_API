package app.api.denuncia.Services.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.Domain;
import app.api.denuncia.Enums.DomainValue;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ReprocessamentoModel;
import app.api.denuncia.Services.DominioService;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Services.ReprocessamentoService;
import app.api.denuncia.Utilities.GlobalFunctions;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private DominioService dominioService;
    private Message message = new Message();
    private ReprocessamentoService reproService;
    private GlobalFunctions gf = new GlobalFunctions();

    public EmailServiceImpl(JavaMailSender javaMailSender, DominioService dominioService,
            ReprocessamentoService reproService) {
        this.javaMailSender = javaMailSender;
        this.dominioService = dominioService;
        this.reproService = reproService;
    }

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Response sendEmail(EmailDetails details, List<String> msg) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(details.getMsgBody(), true);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setFrom(sender);

            javaMailSender.send(mimeMessage);

            msg.add(message.getMessage10());
            return gf.getResponse(1, ResponseType.Sucesso, msg, null);

        } catch (Exception e) {

            DominioModel dom = dominioService.findByDominioAndValor(Domain.TIPO_REPROCESSAMENTO.name(),
                    DomainValue.EMAIL.name());

            ReprocessamentoModel repro = new ReprocessamentoModel();

            repro.setData_criacao(LocalDateTime.now());
            repro.setEmail_user(details.getRecipient());
            repro.setEmail_body(details.getMsgBody());
            repro.setEmail_subject(details.getSubject());
            repro.setEstado(1);
            repro.setLast_user_change(1);
            repro.setTipo_reprocessamento(dom);

            ReprocessamentoModel saveRepro = reproService.saveReprocessamento(repro);

            if (saveRepro == null) {
                System.out.println("ERRO AO SALVAR REPROCESSAMENTO");
            }

            msg.add(message.getMessage11());
            return gf.getResponseError(msg);
        }
    }
}

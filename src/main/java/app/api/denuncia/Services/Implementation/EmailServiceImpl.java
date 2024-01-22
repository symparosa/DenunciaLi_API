package app.api.denuncia.Services.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Services.EmailService;
import app.api.denuncia.Utilities.GlobalFunctions;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private Message message = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Response sendEmail(EmailDetails details, List<String> msg, Object obj) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(details.getMsgBody(), true);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setFrom(sender);

            javaMailSender.send(mimeMessage);

            msg.add(message.getMessage10());
            return gf.getResponse(1, ResponseType.Sucesso, msg, obj);

        } catch (Exception e) {
            msg.add(message.getMessage11());
            return gf.getResponseError(msg);
        }
    }
}

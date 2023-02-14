package app.api.denuncia.Services.Implementation;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.EmailService;

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
    public ResponseModel sendEmail(EmailDetailsModel details, List<String> msg) {

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
            msg.add(message.getMessage11());
            return gf.getResponseError(msg);
        }
    }
}

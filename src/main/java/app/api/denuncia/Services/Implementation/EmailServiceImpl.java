package app.api.denuncia.Services.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

    private JavaMailSender javaMailSender;
    private Message message = new Message();
    private GlobalFunctions gf = new GlobalFunctions();

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}") private String sender;
    
    @Override
    public ResponseModel sendEmail(EmailDetailsModel details, String msg1) {

        List<String> msg = new ArrayList<>();
        msg.add(msg1);

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            
            msg.add(message.getMessage10());
            return gf.getResponse(1, ResponseType.Sucesso, msg, null);
            
        } catch (Exception e) {
            msg.add(message.getMessage11());
            return gf.getResponseError(msg);
        }
    }
}

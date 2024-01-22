package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.Response;

public interface EmailService {

    Response sendEmail(EmailDetails details, List<String> msg, Object obj);
}

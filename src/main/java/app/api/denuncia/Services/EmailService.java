package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;

public interface EmailService {
    
    ResponseModel sendEmail(EmailDetailsModel details, List<String> msg);
}

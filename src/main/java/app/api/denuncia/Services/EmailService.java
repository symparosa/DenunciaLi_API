package app.api.denuncia.Services;

import app.api.denuncia.Models.EmailDetailsModel;
import app.api.denuncia.Models.ResponseModel;

public interface EmailService {
    
    ResponseModel sendEmail(EmailDetailsModel details, String msg);
}

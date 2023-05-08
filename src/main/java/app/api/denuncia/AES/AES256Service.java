package app.api.denuncia.AES;

import app.api.denuncia.Models.ResponseModel;

public interface AES256Service {
    
    public ResponseModel encrypt(String input);

    public String decrypt(String cipherText);
}

package app.api.denuncia.AES;

public interface AES256Service {

    public String encrypt(String input);

    public String decrypt(String cipherText);
}

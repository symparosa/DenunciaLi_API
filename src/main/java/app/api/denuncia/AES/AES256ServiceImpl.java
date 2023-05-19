package app.api.denuncia.AES;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;

@Service
public class AES256ServiceImpl implements AES256Service {

    @Value("${aes56.b64}")
    private String b64;

    @Value("${aes56.algorithm}")
    private String algorithm;

    private IvParameterSpec iv = new IvParameterSpec(new byte[16]);

    @Override
    public String encrypt(String input) {

        Cipher cipher;
        byte[] cipherText;
        SecretKey key = getKey();

        try {

            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            cipherText = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(cipherText);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public String decrypt(String cipherText) {

        Cipher cipher;
        byte[] plainText;
        SecretKey key = getKey();

        try {

            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));

            return new String(plainText);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SecretKey getKey() {

        byte[] decodedKey = Base64.getDecoder().decode(b64);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }
}

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

public class AES256 {

    private final String algorithm = "AES/CBC/PKCS5Padding";
    private final SecretKey key = getKey();
    private final IvParameterSpec iv = new IvParameterSpec(new byte[16]);

    public String encrypt(String input) {

        Cipher cipher;
        byte[] cipherText;

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

    public String decrypt(String cipherText) {

        Cipher cipher;
        byte[] plainText;

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

        String b64 = "ZFbwj1WRRXNx9wCplDhSKuU4YJFCHMB/0oluWSk3skk=";

        byte[] decodedKey = Base64.getDecoder().decode(b64);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }
}

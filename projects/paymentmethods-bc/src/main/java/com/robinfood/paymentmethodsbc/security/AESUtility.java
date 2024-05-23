package com.robinfood.paymentmethodsbc.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AESUtility {
    private SecretKey symmetricKey;

    private IvParameterSpec symmetricIV;

    @Value("${pmbc.security.aes.transformation}")
    private String symmetricTransformation;

    @Value("${pmbc.security.aes.iv}")
    private String symmetricIVStr;

    @Value("${pmbc.security.aes.secret}")
    private String symmetricSecretStr;

    @PostConstruct
    private void init() {
        symmetricKey = decodeSymmetricKey(symmetricSecretStr.getBytes());
        symmetricIV = new IvParameterSpec(symmetricIVStr.getBytes());
    }

    public String decryptText(String content) {
        try {
            return new String(
                    symmetricDecrypt(Base64.getDecoder().decode(content))
            );
        } catch (
                InvalidKeyException
                        | InvalidAlgorithmParameterException
                        | NoSuchAlgorithmException
                        | NoSuchPaddingException
                        | IllegalBlockSizeException
                        | BadPaddingException e
        ) {
            log.error("AES decrypt error", e);
        }
        return null;
    }


    public String encryptText(String content) {
        try {
            return Base64
                    .getEncoder()
                    .encodeToString(symmetricEncrypt(content.getBytes()));
        } catch (
                InvalidKeyException
                        | InvalidAlgorithmParameterException
                        | NoSuchAlgorithmException
                        | NoSuchPaddingException
                        | IllegalBlockSizeException
                        | BadPaddingException e
        ) {
            log.error("AES encrypt error", e);
        }
        return null;
    }


    private byte[] symmetricEncrypt(byte[] plaintext)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(symmetricTransformation);
        cipher.init(Cipher.ENCRYPT_MODE, symmetricKey, symmetricIV);
        return cipher.doFinal(plaintext);
    }


    private byte[] symmetricDecrypt(byte[] ciphertext)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(symmetricTransformation);
        cipher.init(Cipher.DECRYPT_MODE, symmetricKey, symmetricIV);
        return cipher.doFinal(ciphertext);
    }


    private SecretKey decodeSymmetricKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, "AES");
    }
}

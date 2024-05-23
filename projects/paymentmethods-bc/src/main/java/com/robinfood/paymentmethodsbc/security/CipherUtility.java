package com.robinfood.paymentmethodsbc.security;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CipherUtility {
    private static final String CIPHER_TRANSFORMATION = "RSA";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Value("${security.encryption.public-key}")
    private String publicKeyStr;

    @Value("${security.encryption.private-key}")
    private String privateKeyStr;

    @PostConstruct
    private void init()
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        publicKey =
            decodePublicKey(DatatypeConverter.parseHexBinary(publicKeyStr));
        privateKey =
            decodePrivateKey(DatatypeConverter.parseHexBinary(privateKeyStr));
    }

    public static void printRSAKeyPair(int length) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
            CIPHER_TRANSFORMATION
        );
        keyPairGenerator.initialize(length, secureRandom);
        KeyPair keypair = keyPairGenerator.generateKeyPair();

        System.out.println(
            "security.encryption.public-key=" +
            DatatypeConverter.printHexBinary(keypair.getPublic().getEncoded())
        );
        System.out.println(
            "security.encryption.private-key=" +
            DatatypeConverter.printHexBinary(keypair.getPrivate().getEncoded())
        );
    }

    private String encrypt(String content, Key key)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherContent = cipher.doFinal(contentBytes);
        return Base64.getEncoder().encodeToString(cipherContent);
    }

    private String decrypt(String cipherContent, Key key)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherContentBytes = Base64
            .getDecoder()
            .decode(cipherContent.getBytes());
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
        return new String(decryptedContent, StandardCharsets.UTF_8);
    }

    private PublicKey decodePublicKey(byte[] keyBytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_TRANSFORMATION);
        return keyFactory.generatePublic(spec);
    }

    private PrivateKey decodePrivateKey(byte[] keyBytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_TRANSFORMATION);
        return keyFactory.generatePrivate(keySpec);
    }

    public String encryptTextWithPublicKey(String content) {
        try {
            return encrypt(content, publicKey);
        } catch (
            InvalidKeyException
            | NoSuchAlgorithmException
            | NoSuchPaddingException
            | IllegalBlockSizeException
            | BadPaddingException e
        ) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    public String encryptTextWithPrivateKey(String content) {
        try {
            return encrypt(content, privateKey);
        } catch (
            InvalidKeyException
            | NoSuchAlgorithmException
            | NoSuchPaddingException
            | IllegalBlockSizeException
            | BadPaddingException e
        ) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    public String decryptTextWithPrivateKey(String content) {
        try {
            return decrypt(content, privateKey);
        } catch (
            InvalidKeyException
            | NoSuchAlgorithmException
            | NoSuchPaddingException
            | IllegalBlockSizeException
            | BadPaddingException e
        ) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptTextWithPublicKey(String content) {
        try {
            return decrypt(content, publicKey);
        } catch (
            InvalidKeyException
            | NoSuchAlgorithmException
            | NoSuchPaddingException
            | IllegalBlockSizeException
            | BadPaddingException e
        ) {
            e.printStackTrace();
        }
        return null;
    }

    public String translateEncryptionPrivate(String value) {
        String out = decryptTextWithPrivateKey(value);
        if (out != null) {
            out = encryptTextWithPrivateKey(out);
        }
        return out;
    }
}

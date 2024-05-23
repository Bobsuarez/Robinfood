package com.robinfood.customersbc.thirdparties.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.UUID;

@Component
public class CommonPBKDF2Encoder implements PasswordEncoder {
    private static final String SECRET = UUID.randomUUID().toString();

    private static final int ITERATION = 10;

    private static final int KEY_LENGTH = 16;

    /**
     * More info (https://www.owasp.org/index.php/Hashing_Java) 404 :(
     * @param cs password
     * @return encoded password
     */
    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(
                    new PBEKeySpec(
                        cs.toString().toCharArray(),
                        SECRET.getBytes(),
                        ITERATION,
                        KEY_LENGTH
                    )
                )
                .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }
}

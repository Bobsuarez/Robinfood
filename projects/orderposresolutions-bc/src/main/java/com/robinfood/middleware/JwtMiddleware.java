package com.robinfood.middleware;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.exceptions.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static com.robinfood.constants.GeneralConstants.JWT_TOKEN_SECRET;

@Slf4j
public class JwtMiddleware {

    public static void validateToken(String token) {

        try {
            token = token.replace("Bearer", GeneralConstants.DEFAULT_STRING_EMPTY);

            Jwts.parser()
                    .setSigningKey(JWT_TOKEN_SECRET.getBytes(StandardCharsets.ISO_8859_1))
                    .parseClaimsJws(token)
                    .getBody();

            log.info("Successful validation ");

        } catch (ExpiredJwtException e) {
            log.error("Invalid Access (JWT Expired)");
            e.printStackTrace();
            throw new TokenException("Invalid Access (JWT Expired)");
        } catch (SignatureException e) {
            log.error("Error signature invalid: {}", e.getMessage());
            e.printStackTrace();
            throw new TokenException("Invalid Access (JWT signature does not match)");
        } catch (Exception e) {
            log.error("Error in the JWT: {}", e.getMessage());
            e.printStackTrace();
            throw new TokenException("Invalid Access");
        }
    }
}

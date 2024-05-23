package com.robinfood.middleware;

import com.robinfood.exceptions.TokenException;
import com.robinfood.utils.LogsUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.nio.charset.StandardCharsets;

import static com.robinfood.constants.APIConstants.JWT_TOKEN_SECRET;
import static com.robinfood.constants.Constants.DEFAULT_STRING_EMPTY;

public final class JwtMiddleware {

    private JwtMiddleware() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateToken(String token) throws TokenException {

        try {
            token = token.replace("Bearer", DEFAULT_STRING_EMPTY);

            Jwts.parser()
                    .setSigningKey(JWT_TOKEN_SECRET.getBytes(StandardCharsets.ISO_8859_1))
                    .parseClaimsJws(token)
                    .getBody();

            LogsUtil.info("Successful validation ");

        } catch (ExpiredJwtException e) {
            LogsUtil.error("Invalid Access (JWT Expired)" + e);
            throw new TokenException("Invalid Access (JWT Expired)");
        } catch (SignatureException e) {
            LogsUtil.error("Error signature invalid: " + e);
            throw new TokenException("Invalid Access (JWT signature does not match)");
        } catch (Exception e) {
            LogsUtil.error("Error in the JWT: " + e);
            throw new TokenException("Invalid Access");
        }
    }
}

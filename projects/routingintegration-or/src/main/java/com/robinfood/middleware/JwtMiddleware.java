package com.robinfood.middleware;

import com.robinfood.constants.Constants;
import com.robinfood.exceptions.TokenException;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.nio.charset.StandardCharsets;

import static com.robinfood.constants.Constants.JWT_TOKEN_SECRET;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_TOKEN;

public class JwtMiddleware {

    private JwtMiddleware() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateToken(String token) {

        try {
            token = token.replace("Bearer", Constants.DEFAULT_STRING_EMPTY);

            Jwts.parser()
                    .setSigningKey(JWT_TOKEN_SECRET.getBytes(StandardCharsets.ISO_8859_1))
                    .parseClaimsJws(token)
                    .getBody();

            LogsUtil.info("Successful validation ");

        } catch (ExpiredJwtException e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException("Invalid Access (JWT Expired)");
        } catch (SignatureException e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException("Invalid Access (JWT signature does not match)");
        } catch (Exception e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException("Invalid Access");
        }
    }
}

package com.robinfood.middleware;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.exceptions.TokenException;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.robinfood.constants.TokenConstants.JWT_TOKEN_AUD;
import static com.robinfood.constants.TokenConstants.JWT_TOKEN_SECRET;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_EXCEPTION_TOKEN;

public class JwtMiddleware {

    private JwtMiddleware() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateToken(String token) {

        try {

            token = token.replace("Bearer", GeneralConstants.DEFAULT_STRING_EMPTY);

            final Claims claims = Jwts.parser()
                    .setSigningKey(JWT_TOKEN_SECRET.getBytes(StandardCharsets.ISO_8859_1))
                    .parseClaimsJws(token)
                    .getBody();

            String tokenAud = claims.getAudience();

            if (Objects.isNull(tokenAud) || !JWT_TOKEN_AUD.equals(tokenAud)) {
                throw new TokenException("the token audience must be service");
            }
            LogsUtil.info("Successful validation ");

        } catch (ExpiredJwtException e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException("Invalid Access (JWT Expired)");
        } catch (SignatureException | TokenException e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException(e.getMessage());
        } catch (Exception e) {
            LogsUtil.error(ERROR_EXCEPTION_TOKEN.getMessageWithCode(), e, e.getCause());
            throw new TokenException("Invalid Access");
        }
    }
}

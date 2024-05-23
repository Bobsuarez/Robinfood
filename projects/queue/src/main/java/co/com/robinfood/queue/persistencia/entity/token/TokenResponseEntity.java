package co.com.robinfood.queue.persistencia.entity.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static co.com.robinfood.queue.app.lasting.GlobalConstants.ApplicationConfig.AUTHORIZATION_HEADER_BEARER;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenResponseEntity {

    private int code;

    private String messages;

    private ResultResponseEntity result;

    private String status;

    public TokenModelEntity toDomainWithExpirationMinute(Long minutes) {
        return TokenModelEntity.builder()
                .accessToken(AUTHORIZATION_HEADER_BEARER.concat(result.getAccessToken()))
                .expiresIn(minutes)
                .build();
    }

}

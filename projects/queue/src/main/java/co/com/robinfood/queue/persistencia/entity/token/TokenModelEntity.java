package co.com.robinfood.queue.persistencia.entity.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenModelEntity {

    private String accessToken;

    private Long expiresIn;

}

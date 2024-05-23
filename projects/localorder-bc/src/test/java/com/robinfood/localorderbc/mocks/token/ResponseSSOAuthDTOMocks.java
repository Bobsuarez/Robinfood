package com.robinfood.localorderbc.mocks.token;

import com.robinfood.localorderbc.dtos.security.ResponseDataOkSSOAuthDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;

public class ResponseSSOAuthDTOMocks {

    private final String TOKEN_ACCESS_AUTH = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiYXVkIjoibG9naW4iLCJtb2QiOltdLCJwZXIiOltdLCJqdGkiOiJmMzhmOWEwMi04NTZlLTQxZWQtODBjMC02YzkzNmQ0M2UwMWQiLCJleHAiOjE2NTIzNDcyNzAsImlhdCI6MTY1MjMwNDA3MCwibmJmIjowLCJjb21wYW55X2lkIjoxfQ.qPdovzTnlTabY8Ddqkr28R9RYuJxvfRduuAsaCCP50ugGggxhimsJQAvNK53saynGaKbMsXqTeFGG5sPvTo8yQ";

    public final ResponseDataOkSSOAuthDTO responseDataOkSSOAuthDTO = ResponseDataOkSSOAuthDTO
            .builder()
            .accessToken(TOKEN_ACCESS_AUTH)
            .authExpiration(123456L)
            .build();

    public final ResponseSSOAuthDTO responseSSOAuthDTOSuccess = ResponseSSOAuthDTO
            .builder()
            .code(200)
            .result(responseDataOkSSOAuthDTO)
            .status("OK")
            .build();
}

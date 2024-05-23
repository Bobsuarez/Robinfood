package com.robinfood.configurations.dto.v1.security;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestResponse implements Serializable {

    private static final long serialVersionUID = -979112439818239436L;

    private UserDTO user;

    private HttpServletRequest request;
}

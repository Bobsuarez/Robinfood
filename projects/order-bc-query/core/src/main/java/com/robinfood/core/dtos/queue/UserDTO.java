package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 2325192767841801446L;

    private String email;

    private String firstName;

    private Long id;

    private String lastName;

    private String phoneCode;

    private String phoneNumber;
}

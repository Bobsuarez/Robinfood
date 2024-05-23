package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class NewUserDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String mobile;

    private String phoneCode;

}

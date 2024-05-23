package com.robinfood.localprinterbc.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String mobile;

}

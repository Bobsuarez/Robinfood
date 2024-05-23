package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
public class ResponseUserDataDTO {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String mobile;
}

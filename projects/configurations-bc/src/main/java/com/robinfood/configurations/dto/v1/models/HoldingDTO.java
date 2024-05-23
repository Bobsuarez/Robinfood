package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HoldingDTO implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "identification", nullable = false)
    private String identification;

}

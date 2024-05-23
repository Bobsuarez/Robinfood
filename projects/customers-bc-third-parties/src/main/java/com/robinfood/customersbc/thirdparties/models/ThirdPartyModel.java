package com.robinfood.customersbc.thirdparties.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "third_parties")
public class ThirdPartyModel implements Serializable {
    @Serial
    private static final long serialVersionUID = -4005701979497964125L;

    @Id
    private Long id;

    private Long customerId;

    @Transient
    private CustomerModel customer;

    private Integer identificationTypeId;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

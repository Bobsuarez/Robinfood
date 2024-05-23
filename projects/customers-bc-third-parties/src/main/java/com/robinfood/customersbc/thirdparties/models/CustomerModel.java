package com.robinfood.customersbc.thirdparties.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "customers")
public class CustomerModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 4023496568287921073L;

    @Id
    private Long id;
    private Long externalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneCode;
    private String phoneNumber;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

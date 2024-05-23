package com.robinfood.configurations.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores_tax_flow")
public class StoreTax extends AbstractBaseEntity {

    @Column(name = "tax_value", nullable = false)
    private Long value;

    @Column(name = "stores_id", nullable = false)
    private Long storesId;

}

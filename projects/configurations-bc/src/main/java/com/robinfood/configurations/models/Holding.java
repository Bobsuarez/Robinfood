package com.robinfood.configurations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "holdings")
public class Holding extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "identification", nullable = false)
    private String identification;

}

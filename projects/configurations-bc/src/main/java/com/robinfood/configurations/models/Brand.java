package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.BrandSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brands")
@JsonSerialize(using = BrandSerializer.class)
public class Brand extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098610119121444302L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

}

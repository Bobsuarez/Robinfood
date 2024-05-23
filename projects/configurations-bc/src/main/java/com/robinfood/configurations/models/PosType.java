package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.PosTypeSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pos_type")
@JsonSerialize(using = PosTypeSerializer.class)
public class PosType extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098610119771444302L;

    @Column(name = "name", nullable = false)
    private String name;

}

package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.FamilySerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "families")
@JsonSerialize(using = FamilySerializer.class)
public class Family extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "country_id")
    private Long countryId;

    @ManyToOne
    @JoinColumn(name = "family_type_id")
    private FamilyType familyType;

    @Column(name = "status")
    private Integer status;

    public Family(Long id){
        super(id);
    }
}

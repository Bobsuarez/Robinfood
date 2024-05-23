package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.TaxTypeSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tax_types")
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = TaxTypeSerializer.class)
public class TaxType extends AbstractBaseEntity {

    private static final long serialVersionUID = -7382383661484312995L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "status", nullable = false)
    private Integer status;

    public TaxType(Long id){
        super(id);
    }

}

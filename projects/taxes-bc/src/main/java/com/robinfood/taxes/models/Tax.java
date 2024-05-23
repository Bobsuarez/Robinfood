package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.TaxSerializer;
import java.math.BigDecimal;
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
@Table(name = "taxes")
@JsonSerialize(using = TaxSerializer.class)
public class Tax extends AbstractBaseEntity {

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "apply_rules")
    private Boolean applyRules;

    @Column(name = "sap_id")
    private String sapId;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "tax_type_id")
    private TaxType taxType;

    @ManyToOne
    @JoinColumn(name = "expression_id")
    private Expression expression;

    public Tax(Long id) {
        super(id);
    }
}

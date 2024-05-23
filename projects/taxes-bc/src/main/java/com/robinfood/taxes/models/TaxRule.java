package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.TaxRuleSerializer;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "tax_rules")
@JsonSerialize(using = TaxRuleSerializer.class)
public class TaxRule extends AbstractBaseEntity {

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @ManyToOne
    @JoinColumn(name = "left_rule_variable_id")
    private RuleVariable leftVariable;

    @ManyToOne
    @JoinColumn(name = "right_rule_variable_id")
    private RuleVariable rightVariable;

    @ManyToOne
    @JoinColumn(name = "rule_type_id")
    private RuleType ruleType;

    public TaxRule(Long id) {
        super(id);
    }
}

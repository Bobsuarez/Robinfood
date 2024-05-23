package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.RuleVariableSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "rule_variables")
@JsonSerialize(using = RuleVariableSerializer.class)
public class RuleVariable extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "value")
    private String value;

    public RuleVariable(Long id) {
        super(id);
    }
}

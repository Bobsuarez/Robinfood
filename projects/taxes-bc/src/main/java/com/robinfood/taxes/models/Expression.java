package com.robinfood.taxes.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.taxes.serializers.tax.ExpressionSerializer;
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
@Table(name = "expressions")
@JsonSerialize(using = ExpressionSerializer.class)
public class Expression extends AbstractBaseEntity {

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    public Expression(Long id) {
        super(id);
    }

}

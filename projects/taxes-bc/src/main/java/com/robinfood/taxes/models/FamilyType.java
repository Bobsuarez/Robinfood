package com.robinfood.taxes.models;

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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "family_types")
public class FamilyType extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    public FamilyType(Long id) {
        super(id);
    }

}

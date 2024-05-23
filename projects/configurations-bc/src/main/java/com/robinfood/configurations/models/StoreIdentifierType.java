package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.StoreIdentifierTypeSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_identifier_types")
@JsonSerialize(using = StoreIdentifierTypeSerializer.class)
public class StoreIdentifierType extends AbstractBaseEntity {

    private static final long serialVersionUID = 2719289222574384917L;

    @Column(name = "name", nullable = false)
    private String name;

    public StoreIdentifierType(Long id) {
        super(id);
    }

}

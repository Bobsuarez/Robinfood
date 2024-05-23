package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.StoreTypeSerializer;
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
@Table(name = "store_types")
@JsonSerialize(using = StoreTypeSerializer.class)
public class StoreType extends AbstractBaseEntity {

    private static final long serialVersionUID = 2719289222574384917L;

    @Column(name = "name", nullable = false)
    private String name;

    public StoreType(Long id) {
        super(id);
    }

}
